package Model.Entity;

import java.util.Date;

public class TabletEntity extends PortableDeviceEntity {
    public TabletEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, int quantity, double screenSize, OperatingSystem operatingSystem) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, quantity, screenSize, operatingSystem);
    }
}
