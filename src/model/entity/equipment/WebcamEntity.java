package model.entity.equipment;

import java.util.Date;

public class WebcamEntity extends EquipmentEntity {

    private String resolution;

    public WebcamEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, String resolution) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
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
        return super.toString() + " resolution='" + resolution + '\'';
    }
}
