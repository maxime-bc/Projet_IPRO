package model.repository;

import constants.Constants;
import data.ApplicationData;
import data.Status;
import model.entity.borrowing.BorrowingEntity;
import model.entity.equipment.EquipmentEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static constants.Constants.ERROR;
import static constants.Constants.SUCCESS;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

public class BorrowingRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static Status addBorrowing(ArrayList<String> inputs) {
        Status status = new Status();
        if (inputs.size() != 4) {
            status.setStatus(ERROR, ARGS_ERROR);
            return status;
        }
        try {
            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);

            appData.getBorrowingEntities().add(new BorrowingEntity(appData.getCurrentBorrowingId(),
                    BorrowingEntity.BorrowingReason.valueOf(inputs.get(0)), new Date(), format.parse(inputs.get(1)),
                    Integer.parseInt(inputs.get(2)), Integer.parseInt(inputs.get(3))));
            appData.setCurrentBorrowingId(appData.getCurrentBorrowingId() + 1);

            status.setStatus(SUCCESS, ADD);
        } catch (IllegalArgumentException | ParseException e) {
            status.setStatus(ERROR, ADD_ERROR);
        }
        return status;
    }

    public static Status deleteBorrowing(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        for (BorrowingEntity borrowing : appData.getBorrowingEntities()) {
            if (borrowing.getId() == id) {
                appData.getBorrowingEntities().remove(borrowing);
                status.setStatus(SUCCESS, DELETE);
                break;
            }
        }
        return status;
    }

    public static Status updateBorrowing(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, UPDATE_ERROR);
        if (inputs.size() != 4) {
            status.setStatus(ERROR, ARGS_ERROR);
            return status;
        }

        for (BorrowingEntity borrowing : appData.getBorrowingEntities()) {

            if (borrowing.getId() == id) {
                try {
                    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                    borrowing.setReason(BorrowingEntity.BorrowingReason.valueOf(inputs.get(0)));
                    borrowing.setBorrowingStart(format.parse(inputs.get(1)));
                    borrowing.setBorrowingEnd(format.parse(inputs.get(2)));
                    borrowing.setBorrowedEquipmentID(Integer.parseInt(inputs.get(3)));
                    borrowing.setBorrowerID(Integer.parseInt(inputs.get(4)));
                    status.setStatus(SUCCESS, UPDATE);

                } catch (ParseException e) {
                    status.setStatus(ERROR, TYPE_ERROR);
                }
                break;
            }
        }
        return status;
    }

    public static Status returnBorrowing(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);
        int counter = 0;
        for (BorrowingEntity borrowing : appData.getBorrowingEntities()) {
            if (borrowing.getId() == id) {
                for (EquipmentEntity equipmentEntity : appData.getEquipmentEntities()) {
                    if (equipmentEntity.getId() == borrowing.getBorrowedEquipmentID()) {
                        equipmentEntity.setBorrowed(false);
                        status.setStatus(SUCCESS, RETURN);
                        break;
                    }
                }
                appData.getBorrowingEntities().remove(counter);
                break;
            }
            counter++;
        }
        return status;
    }
}