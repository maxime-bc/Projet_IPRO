package model.entity.storage;

import java.io.Serializable;

public class StorageEntity implements Serializable {

    private String storageArea;
    private int managerID;

    public StorageEntity(String storageArea, int managerID) {
        this.storageArea = storageArea;
        this.managerID = managerID;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    @Override
    public String toString() {
        return "storageArea='" + storageArea + '\'' + ", managerID='" + managerID + '\'';
    }
}
