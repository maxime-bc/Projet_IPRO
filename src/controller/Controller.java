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
import java.util.HashMap;
import java.util.Map;

import static constants.Constants.*;
import static constants.ErrorMessages.*;

/**
 * Controller of the application.
 * Links the view with the model.
 */
public class Controller {
    private final View view;
    private final ApplicationData applicationData = ApplicationData.getInstance();

    /**
     * Construct a controller.
     */
    public Controller() {
        this.view = new View();
        loop();
        Serialize.serialize(this.applicationData, APP_DATA_FILE);
    }

    /**
     * Manage the infinite loop of the program.
     */
    private void loop() {
        boolean quit = false;
        while (!quit) {
            quit = this.executeAction(this.view.getAction());
        }
        this.view.closeScanner();
    }

    /**
     * Execute the action entered by the user.
     *
     * @param arguments commands entered by the user. The first string is the action and
     *                  the second is the object on which the action will be applied.
     * @return true if the user has entered the `quit` command, else false.
     */
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
                break;

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

    /**
     * Display objects in the console.
     *
     * @param arguments object to display.
     */
    private void display(String[] arguments) {
        switch (arguments[OBJECT]) {
            case USER_OBJECT:
                this.view.printHashMap(applicationData.getUserEntities());
                break;
            case BORROWING_OBJECT:
                displayBorrowings();
                break;
            case EQUIPMENT_OBJECT:
                displayEquipment();
                break;
            case STORAGE_OBJECT:
                this.view.printHashMap(applicationData.getStorageEntities());
                break;
        }
    }


    /**
     * Display equipment in the console.
     */
    private void displayEquipment() {
        int displayType;
        displayType = this.view.askDisplayType(
                Arrays.asList(TOTAL, AVAILABLE, BORROWED, BY_TYPE, BY_STORAGE_AREA, BY_PURCHASE_DATE),
                "1 - Total\n2 - Available\n3 - Borrowed\n4 - By type\n5 - By storage area\n6 - By purchase date");

        if (displayType == TOTAL) {
            this.view.printHashMap(applicationData.getEquipmentEntities());

        } else if (displayType == AVAILABLE) {
            this.view.printHashMap(EquipmentRepository.getAvailableEquipment());

        } else if (displayType == BORROWED) {
            displayType = this.view.askDisplayType(
                    Arrays.asList(TOTAL, BY_USER),
                    "1 - Display all borrowings\n2 - Display borrowings by user");

            if (displayType == TOTAL) {
                this.view.printHashMap(EquipmentRepository.getBorrowedEquipment());

            } else if (displayType == BY_USER) {
                this.view.printHashMap(applicationData.getUserEntities());
                int borrowerId = this.view.askPositiveInt("Id of the borrower ? > ");

                if (UserRepository.userExists(borrowerId)) {
                    this.view.printHashMap(BorrowingRepository.getBorrowingsByUserId(borrowerId));
                } else {
                    this.view.display(NONEXISTENT_ID);
                }
            }

        } else if (displayType == BY_TYPE) {
            int equipmentType = this.view.askEquipmentType();

            if (Arrays.asList(GAME_CONTROLLER, HEADSET, MOUSE, PHONE, TABLET, VR_CONTROLLER, VR_HEADSET, WEBCAM, MOTION_SENSOR).contains(equipmentType)) {
                this.view.printHashMap(EquipmentRepository.getEquipment(equipmentType));
            } else {
                this.view.display("Equipment type not recognized");
            }

        } else if (displayType == BY_STORAGE_AREA) {
            this.view.printHashMap(applicationData.getStorageEntities());
            int storageAreaId = this.view.askPositiveInt("Id of the storage area ? > ");

            if (StorageRepository.storageAreaExists(storageAreaId)) {
                this.view.printHashMap(EquipmentRepository.getEquipmentByStorageAreaId(storageAreaId));
            } else {
                this.view.display(NONEXISTENT_ID);
            }

        } else if (displayType == BY_PURCHASE_DATE) {
            int numberOfYears = this.view.askPositiveInt("Enter the number of years > ");
            if (numberOfYears > 0) {
                this.view.printHashMap(EquipmentRepository.getEquipmentByNumberOfYears(numberOfYears));
            } else {
                this.view.display("Incorrect number of years.");
            }
        }
    }

    /**
     * Display borrowings in the console.
     */
    private void displayBorrowings() {
        int displayType;
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
            int borrowerId = this.view.askPositiveInt("Id of the borrower ? > ");

            if (UserRepository.userExists(borrowerId)) {
                HashMap<Integer, BorrowingEntity> borrowingEntity = BorrowingRepository.getBorrowingsByUserId(borrowerId);

                if (borrowingEntity.size() > 0) {
                    this.view.display("Borrowings :");
                    this.view.printHashMap(borrowingEntity);

                    this.view.display("Borrowed equipments :");
                    for (Map.Entry<Integer, BorrowingEntity> entry : borrowingEntity.entrySet()) {
                        this.view.printHashMap(EquipmentRepository.getEquipmentById(entry.getValue().getBorrowedEquipmentID()));
                    }
                } else {
                    this.view.display("Empty.");
                }
            } else {
                this.view.display(NONEXISTENT_ID);
            }

        } else if (displayType == OVERDUE) {
            this.view.printHashMap(BorrowingRepository.getOverdueBorrowings());
        }
    }

    /**
     * Add an object.
     *
     * @param arguments name of the object to add.
     */
    private void add(String[] arguments) {
        Status status = new Status();

        switch (arguments[OBJECT]) {
            case USER_OBJECT:
                this.view.printObjectUsage(USER_OBJECT);
                status = UserRepository.addUser(this.view.getUserInput());

                break;
            case BORROWING_OBJECT:
                this.view.printObjectUsage(BORROWING_OBJECT);
                status = BorrowingRepository.addBorrowing(this.view.getUserInput());

                break;
            case EQUIPMENT_OBJECT:
                int type = this.view.askEquipmentType();
                if (type > 0) {

                    this.view.printEquipmentUsage(type);
                    ArrayList<String> inputs = this.view.getUserInput();
                    int quantity = this.view.askPositiveInt("Quantity > ?");

                    for (int i = 0; i < quantity; i++) {
                        status = EquipmentRepository.addEquipment(inputs, type);
                        if (!status.getCode())
                            break;
                    }
                } else {
                    this.view.display("This type is not valid.");
                }
                break;
            case STORAGE_OBJECT:
                this.view.printObjectUsage(STORAGE_OBJECT);
                status = StorageRepository.addStorage(this.view.getUserInput());
                break;
        }

        this.view.display(status.getMessage());

        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }

    /**
     * Update an object.
     *
     * @param arguments name of the object to update.
     */
    private void update(String[] arguments) {
        Status status = new Status();
        String message = "Id of the element on which you want to perform this action ? > ";
        int id;

        switch (arguments[OBJECT]) {
            case USER_OBJECT:
                this.view.printHashMap(applicationData.getUserEntities());
                id = this.view.askPositiveInt(message);
                if (id != -1) {
                    this.view.printObjectUsage(USER_OBJECT);
                    status = UserRepository.updateUser(id, this.view.getUserInput());
                }
                break;

            case (BORROWING_OBJECT):
                this.view.printHashMap(applicationData.getBorrowingEntities());
                id = this.view.askPositiveInt(message);
                if (id != -1) {
                    this.view.printObjectUsage(BORROWING_OBJECT);
                    status = BorrowingRepository.updateBorrowing(id, this.view.getUserInput());
                }
                break;

            case EQUIPMENT_OBJECT:
                this.view.printHashMap(applicationData.getEquipmentEntities());
                id = this.view.askPositiveInt(message);
                if (id != -1) {
                    int type = EquipmentRepository.getEquipmentTypeById(id);
                    if (type == -1) {
                        this.view.display(NONEXISTENT_ID);
                    } else {
                        this.view.printEquipmentUsage(type);
                        status = EquipmentRepository.updateEquipment(id, this.view.getUserInput());
                    }
                }
                break;
            case (STORAGE_OBJECT):
                this.view.printHashMap(applicationData.getStorageEntities());
                id = this.view.askPositiveInt(message);
                if (id != -1) {
                    this.view.printObjectUsage(STORAGE_OBJECT);
                    status = StorageRepository.updateStorage(id, this.view.getUserInput());
                }
                break;
        }

        this.view.display(status.getMessage());
        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }

    /**
     * Return a borrowing.
     */
    private void returnBorrowing() {
        this.view.printHashMap(applicationData.getBorrowingEntities());
        String message = "Id of the borrowing you want to return ? > ";
        int borrowingId = this.view.askPositiveInt(message);

        EquipmentEntity.State equipmentState = EquipmentRepository.getEquipmentState(borrowingId);
        String equipmentName = EquipmentRepository.getEquipmentName(borrowingId);

        this.view.display("In what state is the returned equipment `" +
                equipmentName + "` ? (previous was " + equipmentState + ")");
        EquipmentEntity.State state = this.view.getState();

        if (state != null) {
            Status status = BorrowingRepository.returnBorrowing(borrowingId, state);
            this.view.display(status.getMessage());
            if (status.getCode()) {
                Serialize.serialize(this.applicationData, APP_DATA_FILE);
            }
        }
    }

    /**
     * Delete an object.
     *
     * @param arguments name of the object to object to delete.
     */
    private void delete(String[] arguments) {
        String message;
        int id;
        Status status = new Status();

        switch (arguments[OBJECT]) {
            case USER_OBJECT:
                this.view.printHashMap(applicationData.getUserEntities());
                message = "Id of the element you want to delete ? > ";
                id = this.view.askPositiveInt(message);
                status = UserRepository.deleteUser(id);
                break;

            case EQUIPMENT_OBJECT:
                this.view.printHashMap(applicationData.getEquipmentEntities());
                message = "Id of the element you want to delete ? > ";
                id = this.view.askPositiveInt(message);
                status = EquipmentRepository.deleteEquipment(id);

                break;
            case STORAGE_OBJECT:
                this.view.printHashMap(applicationData.getStorageEntities());
                message = "Id of the element you want to delete ? > ";
                id = this.view.askPositiveInt(message);
                status = StorageRepository.deleteStorage(id);
                break;
        }

        this.view.display(status.getMessage());
        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }
}
