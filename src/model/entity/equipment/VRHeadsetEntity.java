package model.entity.equipment;

import java.util.Date;

public class VRHeadsetEntity extends EquipmentEntity {

    public VRHeadsetEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
    }
}

