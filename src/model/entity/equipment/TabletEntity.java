package model.entity.equipment;

import java.util.Date;

/**
 * Represents a tablet and inherits from a portable device.
 */
public class TabletEntity extends PortableDeviceEntity {

    private boolean withStylus;

    public TabletEntity(EquipmentEntity.Owner owner, String brand, Date purchaseDate, double purchasePrice, EquipmentEntity.State state, boolean isBorrowed, int storageID, double screenSize, OperatingSystem operatingSystem, boolean withStylus) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, screenSize, operatingSystem);
        this.withStylus = withStylus;
    }

    public boolean isWithStylus() {
        return withStylus;
    }

    public void setWithStylus(boolean withStylus) {
        this.withStylus = withStylus;
    }

    @Override
    public String toString() {
        return super.toString() + "withStylus=" + withStylus;
    }
}
