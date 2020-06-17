package model.entity.equipment;

import java.util.Date;

/**
 * Represents a VR controller and inherits from an equipment.
 */
public class VRControllerEntity extends EquipmentEntity {
    public VRControllerEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
    }
}
