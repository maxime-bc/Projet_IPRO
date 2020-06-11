package Model;

import Model.Entity.BorrowingEntity;
import Model.Entity.EquipmentEntity;
import Model.Entity.UserEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class ApplicationData implements Serializable {

    private ArrayList<UserEntity> userEntities = new ArrayList<>();
    private ArrayList<EquipmentEntity> equipmentEntities = new ArrayList<>();
    private ArrayList<BorrowingEntity> borrowingEntities = new ArrayList<>();
    private static ApplicationData applicationData;

    public static ApplicationData getInstance() {
        if (applicationData == null) {
            applicationData = new ApplicationData();
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
}
