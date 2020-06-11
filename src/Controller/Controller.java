package Controller;

import Model.ApplicationData;
import View.View;
import View.ErrorMessages;
import View.Constants;

public class Controller {
    private final View view;
    private final ApplicationData applicationData = ApplicationData.getInstance();

    public Controller() {
        this.view = new View();
        loop();
        Serialize.serialize(this.applicationData, Constants.APP_DATA_FILE);
    }

    private void loop() {
        boolean quit = false;
        while (!quit) {
            quit = this.executeAction(this.view.getAction());
        }
        this.view.closeScanner();
    }

    private boolean executeAction(String[] arguments) {

        if (arguments[0].equals(View.actions.get(0))) {
            display(arguments);

        } else if (arguments[0].equals(View.actions.get(1))) {
            add(arguments);

        } else if (arguments[0].equals(View.actions.get(2))) {
            update(arguments);

        } else if (arguments[0].equals(View.actions.get(3))) {
            delete(arguments);

        } else return arguments[0].equals("quit");

        return false;
    }

    private void display(String[] arguments) {
        Object toDisplay = null;

        if (arguments[1].equals(View.objects.get(0))) {
            toDisplay = applicationData.getUserEntities();
        } else if (arguments[1].equals(View.objects.get(1))) {
            toDisplay = applicationData.getBorrowingEntities();
        } else if (arguments[1].equals(View.objects.get(2))) {
            toDisplay = applicationData.getEquipmentEntities();
        }
        this.view.display(toDisplay);
    }

    private void add(String[] arguments) {
        String msg = "";
        try {
            if (arguments[1].equals(View.objects.get(0))) {
                this.view.printUsage(View.objects.get(0));
                msg = applicationData.addUser(this.view.getUserInput());

            } else if (arguments[1].equals(View.objects.get(1))) {
                this.view.printUsage(View.objects.get(1));
                msg = applicationData.addBorrowing(this.view.getUserInput());

            } else if (arguments[1].equals(View.objects.get(2))) {
                this.view.printUsage(View.objects.get(2));
                msg = applicationData.addEquipment(this.view.getUserInput());
            }

        } catch (NumberFormatException e) {
            msg = ErrorMessages.TYPE_ERROR;
        } catch (ArrayIndexOutOfBoundsException e) {
            msg = ErrorMessages.ARGS_ERROR;
        }
        this.view.display(msg);
    }

    private void update(String[] arguments) {

    }

    private void delete(String[] arguments) {

    }
}
