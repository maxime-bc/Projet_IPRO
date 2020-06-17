package model.entity.equipment;

import java.util.Date;

/**
 * Represents a VR controller and inherits from an equipment.
 */
public class VRControllerEntity extends EquipmentEntity {

    private int nbCaptors;

    public VRControllerEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, int nbCaptors) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
        this.nbCaptors = nbCaptors;
    }

    public int getNbCaptors() {
        return nbCaptors;
    }

    public void setNbCaptors(int nbCaptors) {
        this.nbCaptors = nbCaptors;
    }
}
