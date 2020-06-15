package controller;

import data.ApplicationData;
import data.Status;
import model.entity.borrowing.BorrowingEntity;
import model.entity.equipment.EquipmentEntity;
import model.repository.BorrowingRepository;
import model.repository.EquipmentRepository;
import model.repository.StorageRepository;
import model.repository.UserRepository;
import view.View;

import java.util.ArrayList;
import java.util.Arrays;

import static constants.Constants.*;
import static constants.ErrorMessages.*;


public class Controller {
    private final View view;
    private final ApplicationData applicationData = ApplicationData.getInstance();
    // TODO : format output to arrays ??
    // TODO : add special attributes to classes

    public Controller() {
        this.view = new View();
        loop();
        Serialize.serialize(this.applicationData, APP_DATA_FILE);
    }

    private void loop() {
        boolean quit = false;
        while (!quit) {
            quit = this.executeAction(this.view.getAction());
        }
        this.view.closeScanner();
    }

    private boolean executeAction(String[] arguments) {

        switch (arguments[ACTION]) {
            case DISPLAY_ACTION:
                display(arguments);

                break;
            case ADD_ACTION:
                add(arguments);

                break;
            case UPDATE_ACTION:
                update(arguments);

            case RETURN_ACTION:
                returnBorrowing();

                break;
            case DELETE_ACTION:
                delete(arguments);

                break;
            default:
                return arguments[ACTION].equals(QUIT_ACTION);
        }

        return false;
    }

    private void display(String[] arguments) {
        int displayType;
        switch (arguments[OBJECT]) {
            case USER_OBJECT:
                this.view.printHashMap(applicationData.getUserEntities());
                break;
            case BORROWING_OBJECT:
                displayType = this.view.askDisplayType(Arrays.asList(TOTAL, BY_REASON, BY_BORROWER, OVERDUE),
                        "1 - Total\n2 - By reason\n3 - By borrower\n4 - Overdue");

                if (displayType == TOTAL) {
                    this.view.printHashMap(applicationData.getBorrowingEntities());
                } else if (displayType == BY_REASON) {
                    BorrowingEntity.BorrowingReason borrowingReason = this.view.askBorrowingReason();
                    if (borrowingReason != null) {
                        this.view.printHashMap(BorrowingRepository.getBorrowingsByReason(borrowingReason));
                    }
                } else if (displayType == BY_BORROWER) {
                    int borrowerId = this.view.askInt("Id of the borrower ? > ");
                    this.view.printHashMap(BorrowingRepository.getBorrowingsByUserId(borrowerId));

                } else if (displayType == OVERDUE) {
                    this.view.printHashMap(BorrowingRepository.getOverdueBorrowings());
                }

                this.view.printHashMap(applicationData.getBorrowingEntities());
                break;
            case EQUIPMENT_OBJECT:
                displayType = this.view.askDisplayType(Arrays.asList(TOTAL, AVAILABLE, BORROWED, BY_TYPE),
                        "1 - Total\n2 - Available\n3 - Borrowed\n4 - By type");

                if (displayType == TOTAL) {
                    this.view.printHashMap(applicationData.getEquipmentEntities());
                } else if (displayType == AVAILABLE) {
                    this.view.printHashMap(EquipmentRepository.getAvailableEquipment());
                } else if (displayType == BORROWED) {
                    this.view.printHashMap(EquipmentRepository.getBorrowedEquipment());
                } else if (displayType == BY_TYPE) {
                    int equipmentType = this.view.askEquipmentType();
                    this.view.printHashMap(EquipmentRepository.getEquipment(equipmentType));
                }

                break;
            case STORAGE_OBJECT:
                this.view.printHashMap(applicationData.getStorageEntities());
                break;
        }
    }

    private void add(String[] arguments) {
        Status status = new Status();

        try {
            switch (arguments[OBJECT]) {
                case USER_OBJECT:
                    this.view.printAddUsage(USER_OBJECT);
                    status = UserRepository.addUser(this.view.getUserInput());

                    break;
                case BORROWING_OBJECT:
                    this.view.printAddUsage(BORROWING_OBJECT);
                    status = BorrowingRepository.addBorrowing(this.view.getUserInput());

                    break;
                case EQUIPMENT_OBJECT:
                    int type = this.view.askEquipmentType();
                    this.view.printUsage(type);
                    ArrayList<String> inputs = this.view.getUserInput();
                    int quantity = this.view.askInt("Quantity > ?");

                    for (int i = 0; i < quantity; i++) {
                        status = EquipmentRepository.addEquipment(inputs, type);
                        if (!status.getCode()) {
                            break;
                        }
                    }
                    break;
                case STORAGE_OBJECT:
                    this.view.printAddUsage(STORAGE_OBJECT);
                    status = StorageRepository.addStorage(this.view.getUserInput());
                    break;
            }

        } catch (NumberFormatException e) {
            status.setStatus(ERROR, TYPE_ERROR);

        } catch (ArrayIndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        }
        this.view.display(status.getMessage());

        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }

    private void update(String[] arguments) {
        Status status = new Status();
        String message = "Id of the element on which you want to perform this action ? > ";
        try {
            switch (arguments[OBJECT]) {
                case USER_OBJECT:
                    this.view.printAddUsage(USER_OBJECT);
                    status = UserRepository.updateUser(this.view.askInt(message), this.view.getUserInput());

                    break;
                case (BORROWING_OBJECT):
                    this.view.printAddUsage(BORROWING_OBJECT);
                    status = BorrowingRepository.updateBorrowing(this.view.askInt(message), this.view.getUserInput());

                    break;
                case EQUIPMENT_OBJECT:
                    this.view.printAddUsage(EQUIPMENT_OBJECT);
                    status = EquipmentRepository.updateEquipment(this.view.askInt(message), this.view.getUserInput());

                    break;
                case (STORAGE_OBJECT):
                    this.view.printAddUsage(STORAGE_OBJECT);
                    status = StorageRepository.updateStorage(this.view.askInt(message), this.view.getUserInput());
                    break;
            }

        } catch (NumberFormatException e) {
            status.setStatus(ERROR, TYPE_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        }

        this.view.display(status.getMessage());
        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }

    private void returnBorrowing() {
        String message = "Id of the borrowing you want to return ? > ";
        int borrowingId = this.view.askInt(message);

        EquipmentEntity.State equipmentState = EquipmentRepository.getEquipmentState(borrowingId);

        this.view.display("In what state is the returned (previous was " + equipmentState + ").");
        EquipmentEntity.State state = this.view.getState();

        Status status = BorrowingRepository.returnBorrowing(borrowingId, state);
        this.view.display(status.getMessage());
        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }

    private void delete(String[] arguments) {
        String message = "Id of the element you want to delete ? > ";
        int id = this.view.askInt(message);
        Status status = new Status();

        switch (arguments[OBJECT]) {
            case USER_OBJECT:
                status = UserRepository.deleteUser(id);

                break;
            case BORROWING_OBJECT:
                status = BorrowingRepository.deleteBorrowing(id);

                break;
            case EQUIPMENT_OBJECT:
                status = EquipmentRepository.deleteEquipment(id);

                break;
            case STORAGE_OBJECT:
                status = StorageRepository.deleteStorage(id);
                break;
        }

        this.view.display(status.getMessage());
        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }
}
