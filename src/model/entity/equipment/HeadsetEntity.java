package model.entity.equipment;

import java.util.Date;

public class HeadsetEntity extends EquipmentEntity {

    public HeadsetEntity(int currentId, Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, int quantity) {
        super(currentId, owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, quantity);
    }
}
