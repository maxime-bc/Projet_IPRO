package model.entity.equipment;

import java.util.Date;

/**
 * Represents a portable device and inherits from an equipment.
 */
public class PortableDeviceEntity extends EquipmentEntity {

    public enum OperatingSystem {IOS, ANDROID, WINDOWS}

    private double screenSize;
    private OperatingSystem operatingSystem;

    public PortableDeviceEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, double screenSize, OperatingSystem operatingSystem) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return super.toString() + " screenSize=" + screenSize + ", operatingSystem=" + operatingSystem;
    }
}
