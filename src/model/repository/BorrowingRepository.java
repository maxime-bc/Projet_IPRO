package model.repository;

import constants.Constants;
import data.ApplicationData;
import data.Status;
import model.entity.borrowing.BorrowingEntity;
import model.entity.equipment.EquipmentEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static constants.Constants.ERROR;
import static constants.Constants.SUCCESS;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

public class BorrowingRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static Status addBorrowing(ArrayList<String> inputs) {
        Status status = new Status();

        try {
            int borrowedId = Integer.parseInt(inputs.get(3));

            if (appData.getUserEntities().containsKey(borrowedId)) {
                int equipmentId = Integer.parseInt(inputs.get(2));

                if (appData.getEquipmentEntities().containsKey(equipmentId)) {
                    if (!appData.getEquipmentEntities().get(equipmentId).isBorrowed()) {
                        if (appData.getEquipmentEntities().get(equipmentId).getState() != EquipmentEntity.State.BROKEN) {

                            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                            appData.getBorrowingEntities().put(appData.getCurrentBorrowingId(),
                                    new BorrowingEntity(BorrowingEntity.BorrowingReason.valueOf(inputs.get(0)),
                                            new Date(), format.parse(inputs.get(1)), equipmentId, borrowedId));
                            appData.setCurrentBorrowingId(appData.getCurrentBorrowingId() + 1);
                            appData.getEquipmentEntities().get(equipmentId).setBorrowed(true);
                            status.setStatus(SUCCESS, ADD);

                        } else {
                            status.setStatus(ERROR, BROKEN);
                        }
                    } else {
                        status.setStatus(ERROR, ALREADY_BORROWED);
                    }
                } else {
                    status.setStatus(ERROR, NONEXISTENT_ID);
                }
            } else {
                status.setStatus(ERROR, NONEXISTENT_ID);
            }
        } catch (IllegalArgumentException |
                ParseException e) {
            status.setStatus(ERROR, ADD_ERROR);
        } catch (
                IndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        }
        return status;
    }

    public static Status deleteBorrowing(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getBorrowingEntities().containsKey(id)) {
            appData.getBorrowingEntities().remove(id);
            status.setStatus(SUCCESS, DELETE);
        }
        return status;
    }

    public static Status updateBorrowing(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getBorrowingEntities().containsKey(id)) {
            BorrowingEntity borrowing = appData.getBorrowingEntities().get(id);
            try {
                int borrowerId = Integer.parseInt(inputs.get(4));

                if (appData.getUserEntities().containsKey(borrowerId)) {
                    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                    borrowing.setReason(BorrowingEntity.BorrowingReason.valueOf(inputs.get(0)));
                    borrowing.setBorrowingStart(format.parse(inputs.get(1)));
                    borrowing.setBorrowingEnd(format.parse(inputs.get(2)));
                    borrowing.setBorrowedEquipmentID(Integer.parseInt(inputs.get(3)));
                    borrowing.setBorrowerID(borrowerId);
                    status.setStatus(SUCCESS, UPDATE);

                } else {
                    status.setStatus(ERROR, NONEXISTENT_ID);
                }

            } catch (ParseException | IllegalArgumentException e) {
                status.setStatus(ERROR, TYPE_ERROR);
            } catch (IndexOutOfBoundsException e) {
                status.setStatus(ERROR, ARGS_ERROR);
            }
        }
        return status;
    }

    public static Status returnBorrowing(int id, EquipmentEntity.State state) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getBorrowingEntities().containsKey(id)) {
            BorrowingEntity borrowingEntity = appData.getBorrowingEntities().get(id);

            if (appData.getEquipmentEntities().containsKey(borrowingEntity.getBorrowedEquipmentID())) {
                EquipmentEntity equipmentEntity =
                        appData.getEquipmentEntities().get(borrowingEntity.getBorrowedEquipmentID());
                equipmentEntity.setBorrowed(false);
                equipmentEntity.setState(state);
                status.setStatus(SUCCESS, RETURN);
            }
            appData.getBorrowingEntities().remove(id);
        }
        return status;
    }

    public static HashMap<Integer, BorrowingEntity> getBorrowingsByReason(BorrowingEntity.BorrowingReason borrowingReason) {
        HashMap<Integer, BorrowingEntity> borrowings = new HashMap<>();

        for (Map.Entry<Integer, BorrowingEntity> entry : appData.getBorrowingEntities().entrySet()) {
            int key = entry.getKey();
            BorrowingEntity value = entry.getValue();

            if (value.getReason() == borrowingReason) {
                borrowings.put(key, value);
            }
        }
        return borrowings;
    }

    public static HashMap<Integer, BorrowingEntity> getBorrowingsByUserId(int borrowerId) {
        HashMap<Integer, BorrowingEntity> borrowings = new HashMap<>();

        for (Map.Entry<Integer, BorrowingEntity> entry : appData.getBorrowingEntities().entrySet()) {
            int key = entry.getKey();
            BorrowingEntity value = entry.getValue();

            if (value.getBorrowerID() == borrowerId) {
                borrowings.put(key, value);
            }
        }
        return borrowings;
    }

    public static HashMap<Integer, BorrowingEntity> getOverdueBorrowings() {
        HashMap<Integer, BorrowingEntity> borrowings = new HashMap<>();

        for (Map.Entry<Integer, BorrowingEntity> entry : appData.getBorrowingEntities().entrySet()) {
            int key = entry.getKey();
            BorrowingEntity value = entry.getValue();

            if (value.getBorrowingEnd().before(new Date())) {
                borrowings.put(key, value);
            }
        }
        return borrowings;
    }
}