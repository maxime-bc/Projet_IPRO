package model.repository;

import constants.Constants;
import constants.ErrorMessages;
import constants.SuccessMessages;
import model.ApplicationData;
import model.entity.BorrowingEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BorrowingRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static String addBorrowing(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 4) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);

            appData.getBorrowingEntities().add(new BorrowingEntity(appData.getCurrentBorrowingId(),
                    BorrowingEntity.BorrowingReason.valueOf(inputs.get(0)), new Date(), format.parse(inputs.get(1)),
                    Integer.parseInt(inputs.get(2)), Integer.parseInt(inputs.get(3))));

            appData.setCurrentBorrowingId(appData.getCurrentBorrowingId() + 1);
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException | ParseException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public static String deleteBorrowing(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (BorrowingEntity borrowing : appData.getBorrowingEntities()) {
            if (borrowing.getId() == id) {
                appData.getBorrowingEntities().remove(borrowing);
                message = SuccessMessages.DELETE;
                break;
            }
        }
        return message;
    }

    public static String updateBorrowing(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (BorrowingEntity borrowing : appData.getBorrowingEntities()) {

            if (borrowing.getId() == id) {
                try{
                    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                    borrowing.setReason(BorrowingEntity.BorrowingReason.valueOf(inputs.get(0)));
                    borrowing.setBorrowingStart(format.parse(inputs.get(1)));
                    borrowing.setBorrowingEnd(format.parse(inputs.get(2)));
                    borrowing.setBorrowedEquipmentID(Integer.parseInt(inputs.get(3)));
                    borrowing.setBorrowerID(Integer.parseInt(inputs.get(4)));
                    message = SuccessMessages.UPDATE;

                } catch (ParseException e) {
                    message = ErrorMessages.TYPE_ERROR;
                }
                break;
            }
        }
        return message;
    }
}
