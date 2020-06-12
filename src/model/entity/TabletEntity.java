package model.entity;

import java.util.Date;

public class TabletEntity extends PortableDeviceEntity {
    public TabletEntity(int currentId, Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, int quantity, double screenSize, OperatingSystem operatingSystem) {
        super(currentId, owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, quantity, screenSize, operatingSystem);
    }
}
