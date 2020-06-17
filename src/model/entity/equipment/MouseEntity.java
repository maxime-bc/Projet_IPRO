package model.entity.equipment;

import java.util.Date;

/**
 * Represents a mouse and inherits from an equipment.
 */
public class MouseEntity extends EquipmentEntity {
    public MouseEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
    }
}
