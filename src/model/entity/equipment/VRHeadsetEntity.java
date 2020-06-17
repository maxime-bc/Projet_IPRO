package model.entity.equipment;

import java.util.Date;

/**
 * Represents a VR headset and inherits from an equipment.
 */
public class VRHeadsetEntity extends EquipmentEntity {

    private int refreshRate;

    public VRHeadsetEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, int refreshRate) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
        this.refreshRate = refreshRate;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }
}

