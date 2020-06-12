package model;

import constants.Constants;
import controller.Serialize;
import constants.ErrorMessages;
import constants.SuccessMessages;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static constants.Constants.APP_DATA_FILE;

public class ApplicationData implements Serializable {

    private int currentUserId = 0;
    private int currentBorrowingId = 0;
    private int currentEquipmentId = 0;
    private int currentStorageId = 0;

    private ArrayList<UserEntity> userEntities = new ArrayList<>();
    private ArrayList<EquipmentEntity> equipmentEntities = new ArrayList<>();
    private ArrayList<BorrowingEntity> borrowingEntities = new ArrayList<>();
    private ArrayList<StorageEntity> storageEntities = new ArrayList<>();
    private static ApplicationData applicationData;

    public static ApplicationData getInstance() {
        if (applicationData == null && Serialize.deserialize(APP_DATA_FILE) == null) {
            applicationData = new ApplicationData();
        } else if (applicationData == null) {
            applicationData = (ApplicationData) Serialize.deserialize(APP_DATA_FILE);
        }
        return applicationData;
    }

    public int getCurrentStorageId() {
        return currentStorageId;
    }

    public void setCurrentStorageId(int currentStorageId) {
        this.currentStorageId = currentStorageId;
    }

    public void setStorageEntities(ArrayList<StorageEntity> storageEntities) {
        this.storageEntities = storageEntities;
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

    //TODO : check user inputs
    public String addUser(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 6) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            this.userEntities.add(new UserEntity(currentUserId, inputs.get(0), inputs.get(1),
                    inputs.get(2), inputs.get(3), inputs.get(4), UserEntity.UserType.valueOf(inputs.get(5))));
            this.currentUserId++;
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public String addBorrowing(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 4) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);

            this.borrowingEntities.add(new BorrowingEntity(this.currentBorrowingId, BorrowingEntity.BorrowingReason.valueOf(inputs.get(0)),
                    new Date(), format.parse(inputs.get(1)), Integer.parseInt(inputs.get(2)), Integer.parseInt(inputs.get(3))));
            this.currentBorrowingId++;
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException | ParseException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public String addEquipment(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 7) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);

            this.equipmentEntities.add(new EquipmentEntity(currentEquipmentId, EquipmentEntity.Owner.valueOf(inputs.get(0)),
                    inputs.get(1), format.parse(inputs.get(2)), Double.parseDouble(inputs.get(3)),
                    EquipmentEntity.State.valueOf(inputs.get(4)), false, Integer.parseInt(inputs.get(5)),
                    Integer.parseInt(inputs.get(6))));
            this.currentEquipmentId++;
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException | ParseException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public String addStorage(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 2) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            this.storageEntities.add(new StorageEntity(currentStorageId, inputs.get(0), Integer.parseInt(inputs.get(1))));
            this.currentStorageId++;
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public String deleteUser(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (UserEntity user : userEntities) {
            if (user.getId() == id) {
                userEntities.remove(user);
                message = SuccessMessages.DELETE;
                break;
            }
        }
        return message;
    }

    public String deleteBorrowing(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (BorrowingEntity borrowing : borrowingEntities) {
            if (borrowing.getId() == id) {
                borrowingEntities.remove(borrowing);
                message = SuccessMessages.DELETE;
                break;
            }
        }
        return message;
    }

    public String deleteEquipment(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (EquipmentEntity equipment : equipmentEntities) {
            if (equipment.getId() == id) {
                int quantity = equipment.getQuantity();

                if (quantity > 2) {
                    equipment.setQuantity(quantity - 1);
                } else {
                    // Only one equipment
                    equipmentEntities.remove(equipment);
                    message = SuccessMessages.DELETE;
                    break;
                }
            }
        }
        return message;
    }

    public String deleteStorage(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (StorageEntity storage : storageEntities) {
            if (storage.getId() == id) {
                storageEntities.remove(storage);
                message = SuccessMessages.DELETE;
                break;
            }
        }
        return message;
    }

    public String updateUser(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (UserEntity user : this.userEntities) {
            if (user.getId() == id) {
                user.setFirstName(inputs.get(0));
                user.setLastName(inputs.get(1));
                user.setAddress(inputs.get(2));
                user.setPhoneNumber(inputs.get(3));
                user.setEmail(inputs.get(4));
                user.setUserType(UserEntity.UserType.valueOf(inputs.get(5)));

                message = SuccessMessages.UPDATE;
                break;
            }
        }
        return message;
    }

    public String updateBorrowing(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (BorrowingEntity borrowing : this.borrowingEntities) {

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

    public String updateEquipment(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (EquipmentEntity equipment : this.equipmentEntities) {

            if (equipment.getId() == id) {
                try{
                    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                    equipment.setOwner(EquipmentEntity.Owner.valueOf(inputs.get(0)));
                    equipment.setBrand(inputs.get(1));
                    equipment.setPurchaseDate(format.parse(inputs.get(2)));
                    equipment.setPurchasePrice(Double.parseDouble(inputs.get(3)));
                    equipment.setState(EquipmentEntity.State.valueOf(inputs.get(4)));
                    equipment.setStorageId(Integer.parseInt(inputs.get(5)));

                    message = SuccessMessages.UPDATE;

                } catch (ParseException e) {
                    message = ErrorMessages.TYPE_ERROR;
                }
                break;
            }
        }
        return message;
    }

    public String updateStorage(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (StorageEntity storage : this.storageEntities) {

            if (storage.getId() == id) {
                storage.setStorageArea(inputs.get(0));
                storage.setManagerID(Integer.parseInt(inputs.get(1)));
                message = SuccessMessages.UPDATE;

                break;
            }
        }
        return message;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public int getCurrentBorrowingId() {
        return currentBorrowingId;
    }

    public int getCurrentEquipmentId() {
        return currentEquipmentId;
    }

    public Object getStorageEntities() {
        return storageEntities;
    }
}
