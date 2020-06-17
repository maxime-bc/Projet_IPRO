package model.entity.equipment;

import java.util.Date;

/**
 * Represents a headset and inherits from an equipment.
 */
public class HeadsetEntity extends EquipmentEntity {

    private boolean withMicrophone;

    public HeadsetEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, boolean withMicrophone) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
        this.withMicrophone = withMicrophone;
    }

    public boolean isWithMicrophone() {
        return withMicrophone;
    }

    public void setWithMicrophone(boolean withMicrophone) {
        this.withMicrophone = withMicrophone;
    }
}
