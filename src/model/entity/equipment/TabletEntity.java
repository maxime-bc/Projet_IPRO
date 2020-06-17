package model.entity.equipment;

import java.util.Date;

/**
 * Represents a tablet and inherits from a portable device.
 */
public class TabletEntity extends PortableDeviceEntity {
    public TabletEntity(EquipmentEntity.Owner owner, String brand, Date purchaseDate, double purchasePrice, EquipmentEntity.State state, boolean isBorrowed, int storageID, double screenSize, OperatingSystem operatingSystem) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, screenSize, operatingSystem);
    }
}
