package model.entity.equipment;

import java.util.Date;

public class MouseEntity extends EquipmentEntity {
    public MouseEntity(int currentId, Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID) {
        super(currentId, owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
    }
}