package Model;

import Controller.Serialize;
import Model.Entity.BorrowingEntity;
import Model.Entity.EquipmentEntity;
import Model.Entity.UserEntity;
import View.ErrorMessages;
import View.SuccessMessages;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static View.Constants.APP_DATA_FILE;

public class ApplicationData implements Serializable {

    private ArrayList<UserEntity> userEntities = new ArrayList<>();
    private ArrayList<EquipmentEntity> equipmentEntities = new ArrayList<>();
    private ArrayList<BorrowingEntity> borrowingEntities = new ArrayList<>();
    private static ApplicationData applicationData;

    public static ApplicationData getInstance() {
        if (applicationData == null && Serialize.deserialize(APP_DATA_FILE) == null) {
            applicationData = new ApplicationData();
        } else if (applicationData == null) {
            applicationData = (ApplicationData) Serialize.deserialize(APP_DATA_FILE);
        }
        return applicationData;
    }

    public ArrayList<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(ArrayList<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public ArrayList<EquipmentEntity> getEquipmentEntities() {
        return equipmentEntities;
    }

    public void setEquipmentEntities(ArrayList<EquipmentEntity> equipmentEntities) {
        this.equipmentEntities = equipmentEntities;
    }

    public ArrayList<BorrowingEntity> getBorrowingEntities() {
        return borrowingEntities;
    }

    public void setBorrowingEntities(ArrayList<BorrowingEntity> borrowingEntities) {
        this.borrowingEntities = borrowingEntities;
    }

    @Override
    public String toString() {
        return "ApplicationData{" +
                "userEntities=" + userEntities +
                ", equipmentEntities=" + equipmentEntities +
                ", borrowingEntities=" + borrowingEntities +
                '}';
    }

    public String addUser(String[] inputs) {
        String message;
        if (inputs.length != 6) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            this.userEntities.add(new UserEntity(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], UserEntity.UserType.valueOf(inputs[5])));
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public String addBorrowing(String[] inputs) {
        String message;
        if (inputs.length != 4) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.FRANCE);

            this.borrowingEntities.add(new BorrowingEntity(BorrowingEntity.BorrowingReason.valueOf(inputs[0]),
                    new Date(), format.parse(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3])));
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException | ParseException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public String addEquipment(String[] inputs) {
        String message;
        if (inputs.length != 7) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.FRANCE);

            this.equipmentEntities.add(new EquipmentEntity(EquipmentEntity.Owner.valueOf(inputs[0]),
                    inputs[1], format.parse(inputs[2]), Double.parseDouble(inputs[3]),
                    EquipmentEntity.State.valueOf(inputs[4]), false, Integer.parseInt(inputs[5]),
                    Integer.parseInt(inputs[6])));
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException | ParseException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }
}
