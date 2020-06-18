package model.entity.equipment;

import java.util.Date;

/**
 * Represents a motion sensor and inherits from an equipment.
 */
public class MotionSensorEntity extends EquipmentEntity {

    private float scope;

    public MotionSensorEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, float scope) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
        this.scope = scope;
    }

    public float getScope() {
        return scope;
    }

    public void setScope(float scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return super.toString() + "scope=" + scope;
    }
}
