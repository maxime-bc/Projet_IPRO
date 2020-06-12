package controller;

import constants.ErrorMessages;
import model.ApplicationData;
import model.repository.BorrowingRepository;
import model.repository.EquipmentRepository;
import model.repository.StorageRepository;
import model.repository.UserRepository;
import view.View;

import static constants.Constants.*;


public class Controller {
    private final View view;
    private final ApplicationData applicationData = ApplicationData.getInstance();

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
        // TODO Change return type to bool and print message directly in methods (save even if error) + bug serialization ?
        String msg = "";
        try {
            if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
                this.view.printAddUsage(View.objects.get(USER_OBJECT));
                msg = UserRepository.addUser(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
                this.view.printAddUsage(View.objects.get(BORROWING_OBJECT));
                msg = BorrowingRepository.addBorrowing(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
                this.view.printAddUsage(View.objects.get(EQUIPMENT_OBJECT));
                msg = EquipmentRepository.addEquipment(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
                this.view.printAddUsage(View.objects.get(STORAGE_OBJECT));
                msg = StorageRepository.addStorage(this.view.getUserInput());
            }

        } catch (NumberFormatException e) {
            msg = ErrorMessages.TYPE_ERROR;
        } catch (ArrayIndexOutOfBoundsException e) {
            msg = ErrorMessages.ARGS_ERROR;
        }
        this.view.display(msg);
        Serialize.serialize(this.applicationData, APP_DATA_FILE);
    }

    private void update(String[] arguments) {
        // TODO : delete Usage
        String msg = "";
        try {
            if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
                this.view.printAddUsage(View.objects.get(USER_OBJECT));
                msg = UserRepository.updateUser(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
                this.view.printAddUsage(View.objects.get(BORROWING_OBJECT));
                msg = BorrowingRepository.updateBorrowing(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
                this.view.printAddUsage(View.objects.get(EQUIPMENT_OBJECT));
                msg = EquipmentRepository.updateEquipment(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
                this.view.printAddUsage(View.objects.get(STORAGE_OBJECT));
                msg = StorageRepository.updateStorage(this.view.getIdOfElement(), this.view.getUserInput());
            }

        } catch (NumberFormatException e) {
            msg = ErrorMessages.TYPE_ERROR;
        } catch (ArrayIndexOutOfBoundsException e) {
            msg = ErrorMessages.ARGS_ERROR;
        }

        this.view.display(msg);
        Serialize.serialize(this.applicationData, APP_DATA_FILE);
    }

    private void delete(String[] arguments) {
        int id = this.view.getIdOfElement();
        String msg = "";

        if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
            msg = UserRepository.deleteUser(id);

        } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
            msg = BorrowingRepository.deleteBorrowing(id);

        } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
            msg = EquipmentRepository.deleteEquipment(id);

        } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
            msg = StorageRepository.deleteStorage(id);
        }

        this.view.display(msg);
        Serialize.serialize(this.applicationData, APP_DATA_FILE);
    }
}
