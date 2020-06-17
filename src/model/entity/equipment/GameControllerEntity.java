package model.entity.equipment;

import java.util.Date;

/**
 * Represents a game controller and inherits from an equipment.
 */
public class GameControllerEntity extends EquipmentEntity {
    public GameControllerEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
    }
}
