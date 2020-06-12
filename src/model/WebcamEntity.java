package model;

import java.util.Date;

public class WebcamEntity extends EquipmentEntity {

    private String resolution;

    public WebcamEntity(int currentId, Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, int quantity, String resolution) {
        super(currentId, owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, quantity);
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "WebcamEntity{" + super.toString() +
                "resolution='" + resolution + '\'' +
                '}';
    }
}
