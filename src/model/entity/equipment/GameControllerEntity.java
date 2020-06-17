package model.entity.equipment;

import java.util.Date;

/**
 * Represents a game controller and inherits from an equipment.
 */
public class GameControllerEntity extends EquipmentEntity {


    private String console;

    public GameControllerEntity(Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, String console) {
        super(owner, brand, purchaseDate, purchasePrice, state, isBorrowed, storageID);
        this.console = console;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

}
