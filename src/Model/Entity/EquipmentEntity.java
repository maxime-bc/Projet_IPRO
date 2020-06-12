package Model.Entity;

import java.io.Serializable;
import java.util.Date;

public class EquipmentEntity implements Serializable {

    public enum State {NEW, GOOD, USED, BROKEN}
    public enum Owner {ENSIIE, TSP, C19, UEVE}

    private final int id;
    private Owner owner;
    private String brand;
    private Date purchaseDate;
    private double purchasePrice;
    private State state;
    private boolean isBorrowed;
    private int storageID, quantity;

    public EquipmentEntity(int currentId, Owner owner, String brand, Date purchaseDate, double purchasePrice, State state, boolean isBorrowed, int storageID, int quantity) {
        this.id = currentId;
        this.owner = owner;
        this.brand = brand;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.state = state;
        this.isBorrowed = isBorrowed;
        this.storageID = storageID;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "EquipmentEntity{" +
                "ID=" + id +
                ", brand='" + brand + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", purchasePrice=" + purchasePrice +
                ", state=" + state +
                ", isBorrowed=" + isBorrowed +
                ", storageID=" + storageID +
                ", quantity=" + quantity +
                '}';
    }
}
