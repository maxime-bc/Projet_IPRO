package model.entity.equipment;

import java.util.Date;

/**
 * Represents a headset and inherits from an equipment.
 */
public class HeadsetEntity extends EquipmentEntity {

    public HeadsetEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
    }
}
