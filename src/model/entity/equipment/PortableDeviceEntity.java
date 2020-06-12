package model.entity.equipment;

import java.util.Date;

public class PortableDeviceEntity extends EquipmentEntity {

    public enum OperatingSystem {IOS, ANDROID, WINDOWS}

    private double screenSize;
    private OperatingSystem operatingSystem;

    public PortableDeviceEntity(int currentId, Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, double screenSize, OperatingSystem operatingSystem) {
        super(currentId, owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
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
        return "PortableDeviceEntity{" + super.toString() +
                "screenSize=" + screenSize +
                ", operatingSystem=" + operatingSystem +
                '}';
    }
}
