package Model.Entity;

import java.io.Serializable;
import java.util.Date;

public class BorrowingEntity implements Serializable {

    public enum BorrowingReason {JIN_PROJECT, JIN_UE, Y2_UE, ENSIIE, PERSONAL_WORK, STARTUP, DEMO}

    private final int id;
    private BorrowingReason reason;
    private Date borrowingStart, borrowingEnd;
    private int borrowedEquipmentID, borrowerID;

    public BorrowingEntity(int currentID, BorrowingReason reason, Date borrowingStart, Date borrowingEnd, int borrowedEquipmentID, int borrowerID){
        this.id = currentID;
        this.reason = reason;
        this.borrowingStart = borrowingStart;
        this.borrowingEnd = borrowingEnd;
        this.borrowedEquipmentID = borrowedEquipmentID;
        this.borrowerID = borrowerID;
    }

    public int getId() {
        return id;
    }

    public BorrowingReason getReason() {
        return reason;
    }

    public void setReason(BorrowingReason reason) {
        this.reason = reason;
    }

    public Date getBorrowingStart() {
        return borrowingStart;
    }

    public void setBorrowingStart(Date borrowingStart) {
        this.borrowingStart = borrowingStart;
    }

    public Date getBorrowingEnd() {
        return borrowingEnd;
    }

    public void setBorrowingEnd(Date borrowingEnd) {
        this.borrowingEnd = borrowingEnd;
    }

    public int getBorrowedEquipmentID() {
        return borrowedEquipmentID;
    }

    public void setBorrowedEquipmentID(int borrowedEquipmentID) {
        this.borrowedEquipmentID = borrowedEquipmentID;
    }

    public int getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    @Override
    public String toString() {
        return "BorrowingEntity{" +
                "ID=" + id +
                ", reason=" + reason +
                ", borrowingStart=" + borrowingStart +
                ", borrowingEnd=" + borrowingEnd +
                ", borrowedEquipmentID=" + borrowedEquipmentID +
                ", borrowerID=" + borrowerID +
                '}';
    }
}
