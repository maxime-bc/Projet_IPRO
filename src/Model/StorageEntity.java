package Model;

public class StorageEntity {

    private final int ID;
    private static int currentID = 0;
    private String storageArea;
    private int managerID;

    public StorageEntity(String storageArea, int managerID){
        this.ID = currentID;
        StorageEntity.currentID += 1;
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
        return "StorageEntity{" +
                "ID=" + ID +
                ", storageArea='" + storageArea + '\'' +
                ", managerID=" + managerID +
                '}';
    }
}
