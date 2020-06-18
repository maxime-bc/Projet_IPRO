package model.entity.equipment;

import java.util.Date;

/**
 * Represents a phone and inherits from a portable device.
 */
public class PhoneEntity extends PortableDeviceEntity {

    private int nbSimCard;

    public PhoneEntity(EquipmentEntity.Owner owner, String brand, Date purchaseDate, double purchasePrice, EquipmentEntity.State state, boolean isBorrowed, int storageID, double screenSize, OperatingSystem operatingSystem, int nbSimCard) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID, screenSize, operatingSystem);
        this.nbSimCard = nbSimCard;
    }

    public int getNbSimCard() {
        return nbSimCard;
    }

    public void setNbSimCard(int nbSimCard) {
        this.nbSimCard = nbSimCard;
    }

    @Override
    public String toString() {
        return super.toString() + "nbSimCard=" + nbSimCard;
    }
}
