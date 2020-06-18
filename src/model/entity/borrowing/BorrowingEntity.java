package model.entity.borrowing;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static constants.Constants.DATE_FORMAT_PATTERN;

/**
 * Represents a borrowing.
 */
public class BorrowingEntity implements Serializable {

    public enum BorrowingReason {JIN_PROJECT, JIN_UE, Y2_UE, ENSIIE, PERSONAL_WORK, STARTUP, DEMO}

    private BorrowingReason reason;
    private Date borrowingStart, borrowingEnd;
    private int borrowedEquipmentID, borrowerID;

    public BorrowingEntity(BorrowingReason reason, Date borrowingStart, Date borrowingEnd, int borrowedEquipmentID, int borrowerID) {
        this.reason = reason;
        this.borrowingStart = borrowingStart;
        this.borrowingEnd = borrowingEnd;
        this.borrowedEquipmentID = borrowedEquipmentID;
        this.borrowerID = borrowerID;
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
        return String.format("%-20s %-30s %-30s %30s %20s",
                "reason=" + reason,
                "borrowingStart=" + new SimpleDateFormat(DATE_FORMAT_PATTERN).format(borrowingStart),
                "borrowingEnd=" + new SimpleDateFormat(DATE_FORMAT_PATTERN).format(borrowingEnd),
                "borrowedEquipmentID=" + borrowedEquipmentID,
                "borrowerID=" + borrowerID);
    }
}
