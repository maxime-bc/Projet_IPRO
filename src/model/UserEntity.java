package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserEntity implements Serializable {

    public enum UserType {JIN_STUDENT, Y2_STUDENT, ENSIIE, C19, TEACHER, OTHER}

    private final int ID;
    private String firstName, lastName, address, phoneNumber, email;

    private UserType userType;
    private ArrayList<BorrowingEntity> borrowedEquipments = new ArrayList<>();

    public UserEntity(int currentId, String firstName, String lastName, String address, String phoneNumber, String email, UserType userType) {
        this.ID = currentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = userType;
    }

    public int getId() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) { this.userType = userType; }

    public ArrayList<BorrowingEntity> getBorrowedEquipments() {
        return borrowedEquipments;
    }

    public void setBorrowedEquipments(ArrayList<BorrowingEntity> borrowedEquipments) {
        this.borrowedEquipments = borrowedEquipments;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", borrowedEquipments=" + borrowedEquipments +
                "}\n";
    }
}
