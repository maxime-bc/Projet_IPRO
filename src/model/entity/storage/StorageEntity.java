package model.entity.storage;

import java.io.Serializable;

public class StorageEntity implements Serializable {

    private final int id;
    private String storageArea;
    private int managerID;

    public StorageEntity(int currentId, String storageArea, int managerID){
        this.id = currentId;
        this.storageArea = storageArea;
        this.managerID = managerID;
    }

    public int getId() {
        return id;
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
        return "StorageEntity{" +
                "ID=" + id +
                ", storageArea='" + storageArea + '\'' +
                ", managerID=" + managerID +
                '}';
    }
}
