package data;

import controller.Serialize;
import model.entity.borrowing.BorrowingEntity;
import model.entity.equipment.EquipmentEntity;
import model.entity.storage.StorageEntity;
import model.entity.user.UserEntity;
import test.Test;

import java.io.Serializable;
import java.util.HashMap;

import static constants.Constants.APP_DATA_FILE;

public class ApplicationData implements Serializable {

    private int currentUserId = 0;
    private int currentBorrowingId = 0;
    private int currentEquipmentId = 0;
    private int currentStorageId = 0;

    private HashMap<Integer, UserEntity> userEntities = new HashMap<>();
    private HashMap<Integer, EquipmentEntity> equipmentEntities = new HashMap<>();
    private HashMap<Integer, BorrowingEntity> borrowingEntities = new HashMap<>();
    private HashMap<Integer, StorageEntity> storageEntities = new HashMap<>();
    private static ApplicationData applicationData;

    public static ApplicationData getInstance() {
        if (applicationData == null && Serialize.deserialize(APP_DATA_FILE) == null) {
            applicationData = new ApplicationData();
            //TODO : remove the following line when finished
            new Test();
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

    public HashMap<Integer, UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(HashMap<Integer, UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public HashMap<Integer, EquipmentEntity> getEquipmentEntities() {
        return equipmentEntities;
    }

    public void setEquipmentEntities(HashMap<Integer, EquipmentEntity> equipmentEntities) {
        this.equipmentEntities = equipmentEntities;
    }

    public HashMap<Integer, BorrowingEntity> getBorrowingEntities() {
        return borrowingEntities;
    }

    public void setBorrowingEntities(HashMap<Integer, BorrowingEntity> borrowingEntities) {
        this.borrowingEntities = borrowingEntities;
    }

    public HashMap<Integer, StorageEntity> getStorageEntities() {
        return storageEntities;
    }

    public void setStorageEntities(HashMap<Integer, StorageEntity> storageEntities) {
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
