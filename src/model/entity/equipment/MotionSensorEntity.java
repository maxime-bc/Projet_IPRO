package model.entity.equipment;

import java.util.Date;

public class MotionSensorEntity extends EquipmentEntity {
    public MotionSensorEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
    }
}
