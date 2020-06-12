package controller;

import data.ApplicationData;
import data.Status;
import model.repository.BorrowingRepository;
import model.repository.EquipmentRepository;
import model.repository.StorageRepository;
import model.repository.UserRepository;
import view.View;

import java.util.ArrayList;

import static constants.Constants.*;
import static constants.ErrorMessages.*;


public class Controller {
    private final View view;
    private final ApplicationData applicationData = ApplicationData.getInstance();
    // TODO : remove quantity and create 1 object each time
    // TODO : ask user which type of equipment he wants to add
    // TODO : change toString
    // https://www.sqlitetutorial.net/sqlite-java/
    // https://openclassrooms.com/fr/courses/1469021-les-applications-web-avec-javafx/1469170-les-pre-requis

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

        if (arguments[ACTION].equals(View.actions.get(DISPLAY_ACTION))) {
            display(arguments);

        } else if (arguments[ACTION].equals(View.actions.get(ADD_ACTION))) {
            add(arguments);

        } else if (arguments[ACTION].equals(View.actions.get(UPDATE_ACTION))) {
            update(arguments);

        } else if (arguments[ACTION].equals(View.actions.get(DELETE_ACTION))) {
            delete(arguments);

        } else return arguments[ACTION].equals(View.actions.get(QUIT_ACTION));

        return false;
    }

    private void display(String[] arguments) {
        Object toDisplay = null;

        if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
            toDisplay = applicationData.getUserEntities();
        } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
            toDisplay = applicationData.getBorrowingEntities();
        } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
            toDisplay = applicationData.getEquipmentEntities();
        } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
            toDisplay = applicationData.getStorageEntities();
        }

        this.view.display(toDisplay);
    }

    private void add(String[] arguments) {
        Status status = new Status();

        try {
            if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
                this.view.printAddUsage(View.objects.get(USER_OBJECT));
                status = UserRepository.addUser(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
                this.view.printAddUsage(View.objects.get(BORROWING_OBJECT));
                status = BorrowingRepository.addBorrowing(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
                this.view.printAddUsage(View.objects.get(EQUIPMENT_OBJECT));
                ArrayList<String> inputs = this.view.getUserInput();
                int quantity = this.view.askQuantity();

                for(int i = 0; i < quantity; i++){
                    status = EquipmentRepository.addEquipment(inputs);
                    if (!status.getCode()) { break; }
                }

            } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
                this.view.printAddUsage(View.objects.get(STORAGE_OBJECT));
                status = StorageRepository.addStorage(this.view.getUserInput());
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
        try {
            if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
                this.view.printAddUsage(View.objects.get(USER_OBJECT));
                status = UserRepository.updateUser(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
                this.view.printAddUsage(View.objects.get(BORROWING_OBJECT));
                status = BorrowingRepository.updateBorrowing(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
                this.view.printAddUsage(View.objects.get(EQUIPMENT_OBJECT));
                status = EquipmentRepository.updateEquipment(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
                this.view.printAddUsage(View.objects.get(STORAGE_OBJECT));
                status = StorageRepository.updateStorage(this.view.getIdOfElement(), this.view.getUserInput());
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

    private void delete(String[] arguments) {
        int id = this.view.getIdOfElement();
        Status status = new Status();

        if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
            status = UserRepository.deleteUser(id);

        } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
            status = BorrowingRepository.deleteBorrowing(id);

        } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
            status = EquipmentRepository.deleteEquipment(id);

        } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
            status = StorageRepository.deleteStorage(id);
        }

        this.view.display(status.getMessage());
        if (status.getCode()) {
            Serialize.serialize(this.applicationData, APP_DATA_FILE);
        }
    }
}
