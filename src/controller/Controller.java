package controller;

import constants.ErrorMessages;
import model.ApplicationData;
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

        //TODO: all actions for storage
        if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
            toDisplay = applicationData.getUserEntities();
        } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
            toDisplay = applicationData.getBorrowingEntities();
        } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
            toDisplay = applicationData.getEquipmentEntities();
        } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))){
            toDisplay = applicationData.getStorageEntities();
        }

        this.view.display(toDisplay);
    }

    private void add(String[] arguments) {
        // TODO Change return type to bool and print message directly in methods (save even if error) + bug serialization ?
        String msg = "";
        try {
            if (arguments[OBJECT].equals(View.objects.get(USER_OBJECT))) {
                this.view.printUsage(View.objects.get(USER_OBJECT));
                msg = applicationData.addUser(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
                this.view.printUsage(View.objects.get(BORROWING_OBJECT));
                msg = applicationData.addBorrowing(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
                this.view.printUsage(View.objects.get(EQUIPMENT_OBJECT));
                msg = applicationData.addEquipment(this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
                this.view.printUsage(View.objects.get(STORAGE_OBJECT));
                msg = applicationData.addStorage(this.view.getUserInput());
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
                this.view.printUsage(View.objects.get(USER_OBJECT));
                msg = applicationData.updateUser(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
                this.view.printUsage(View.objects.get(BORROWING_OBJECT));
                msg = applicationData.updateBorrowing(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
                this.view.printUsage(View.objects.get(EQUIPMENT_OBJECT));
                msg = applicationData.updateEquipment(this.view.getIdOfElement(), this.view.getUserInput());

            } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
                this.view.printUsage(View.objects.get(STORAGE_OBJECT));
                msg = applicationData.updateStorage(this.view.getIdOfElement(), this.view.getUserInput());
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
            msg = applicationData.deleteUser(id);

        } else if (arguments[OBJECT].equals(View.objects.get(BORROWING_OBJECT))) {
            msg = applicationData.deleteBorrowing(id);

        } else if (arguments[OBJECT].equals(View.objects.get(EQUIPMENT_OBJECT))) {
            msg = applicationData.deleteEquipment(id);

        } else if (arguments[OBJECT].equals(View.objects.get(STORAGE_OBJECT))) {
            msg = applicationData.deleteStorage(id);
        }

        this.view.display(msg);
        Serialize.serialize(this.applicationData, APP_DATA_FILE);
    }
}
