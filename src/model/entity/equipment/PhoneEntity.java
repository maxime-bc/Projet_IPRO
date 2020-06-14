package model.entity.equipment;

import java.util.Date;

public class PhoneEntity extends PortableDeviceEntity {

    public PhoneEntity(EquipmentEntity.Owner owner, String brand, Date purchaseDate, double purchasePrice, EquipmentEntity.State state, boolean isBorrowed, int storageID, double screenSize, OperatingSystem operatingSystem) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, screenSize, operatingSystem);
    }
}
