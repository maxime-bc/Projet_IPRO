package data;

import controller.Serialize;
import model.entity.BorrowingEntity;
import model.entity.EquipmentEntity;
import model.entity.StorageEntity;
import model.entity.UserEntity;

import java.io.Serializable;
import java.util.ArrayList;

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

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public int getCurrentBorrowingId() {
        return currentBorrowingId;
    }

    public void setCurrentBorrowingId(int currentBorrowingId) {
        this.currentBorrowingId = currentBorrowingId;
    }

    public int getCurrentEquipmentId() {
        return currentEquipmentId;
    }

    public void setCurrentEquipmentId(int currentEquipmentId) {
        this.currentEquipmentId = currentEquipmentId;
    }

    public int getCurrentStorageId() {
        return currentStorageId;
    }

    public void setCurrentStorageId(int currentStorageId) {
        this.currentStorageId = currentStorageId;
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

    public ArrayList<StorageEntity> getStorageEntities() {
        return storageEntities;
    }

    public void setStorageEntities(ArrayList<StorageEntity> storageEntities) {
        this.storageEntities = storageEntities;
    }

    @Override
    public String toString() {
        return "ApplicationData{" +
                "userEntities=" + userEntities +
                ", equipmentEntities=" + equipmentEntities +
                ", borrowingEntities=" + borrowingEntities +
                '}';
    }
}
