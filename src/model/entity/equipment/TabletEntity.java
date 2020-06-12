package model.entity.equipment;

import java.util.Date;

public class TabletEntity extends PortableDeviceEntity {
    public TabletEntity(int currentId, EquipmentEntity.Owner owner, String brand, Date purchaseDate, double purchasePrice, EquipmentEntity.State state, boolean isBorrowed, int storageID, double screenSize, OperatingSystem operatingSystem) {
        super(currentId, owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, screenSize, operatingSystem);
    }
}