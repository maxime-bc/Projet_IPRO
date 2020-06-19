package model.entity.equipment;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static constants.Constants.DATE_FORMAT_PATTERN;

/**
 * Represents an equipment.
 */
public class EquipmentEntity implements Serializable {

    public enum State {NEW, GOOD, USED, BROKEN}

    public enum Owner {ENSIIE, TSP, C19, UEVE}

    private Owner owner;
    private String name;
    private Date purchaseDate;
    private double purchasePrice;
    private State state;
    private boolean isBorrowed;
    private int storageID;

    public EquipmentEntity(Owner owner, String name, Date purchaseDate, double purchasePrice,
                           State state, boolean isBorrowed, int storageID) {
        this.owner = owner;
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.state = state;
        this.isBorrowed = isBorrowed;
        this.storageID = storageID;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public int getStorageID() {
        return storageID;
    }

    public void setStorageId(int storageID) {
        this.storageID = storageID;
    }

    @Override
    public String toString() {
        return String.format("%s %-30s %-25s %-20s %-15s %-20s %-15s",
                "owner=" + owner,
                "name='" + name + '\'' ,
                "purchaseDate=" + new SimpleDateFormat(DATE_FORMAT_PATTERN).format(purchaseDate) ,
                "purchasePrice=" + purchasePrice ,
                "state=" + state ,
                "isBorrowed=" + isBorrowed ,
                "storageID=" + storageID);
    }
}
