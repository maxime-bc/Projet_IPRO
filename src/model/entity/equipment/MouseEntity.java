package model.entity.equipment;

import java.util.Date;

/**
 * Represents a mouse and inherits from an equipment.
 */
public class MouseEntity extends EquipmentEntity {

    private float dpi;

    public MouseEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, float dpi) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
        this.dpi = dpi;
    }

    public float getDpi() {
        return dpi;
    }

    public void setDpi(float dpi) {
        this.dpi = dpi;
    }
}
