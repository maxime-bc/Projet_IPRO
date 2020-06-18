package view;

import model.entity.borrowing.BorrowingEntity;
import model.entity.equipment.EquipmentEntity;
import model.entity.equipment.MotionSensorEntity;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.Constants.*;

/**
 * View of the application.
 * Called by the controller to display information in console.
 */
public class View {
    private final Scanner scanInput;

    /**
     * Constructs a view.
     */
    public View() {
        printMenu();
        this.scanInput = new Scanner(System.in);
    }

    /**
     * Prints the appliation menu.
     */
    private void printMenu() {
        display("******************************* MENU ********************************");
        display("**   Actions : display, add, update, delete, return, quit          **");
        display("**   On what data type : user, borrowing, equipment, storage       **");
        display("**   Ex : add user, display user, add storage, delete equipment... **");
        display("**   Other actions : return, quit                                  **");
        display("*********************************************************************");
    }

    /**
     * Prints the usage of an action.
     * @param object object for which we want to print the usage (ex: user, borrowing or storage).
     *               For an equipment object, use printEquipmentUsage().
     */
    public void printObjectUsage(String object) {
        switch (object) {
            case USER_OBJECT:
                display("<first_name> <last_name> <address> <phone_number> <email> <user_type : JIN_STUDENT | Y2_STUDENT | ENSIIE | C19 | TEACHER | OTHER>");
                break;
            case BORROWING_OBJECT:
                display("<reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO> " +
                        "<borrowing end: dd/mm/yyyy> <borrowed_equipment_id> <borrower_id>");
                break;
            case STORAGE_OBJECT:
                display("<storage_area> <manager_id>");
                break;
        }
    }

    /**
     * Asks a positive integer to the user, with a given message.
     * @param message a message which is going to be printed before asking an integer to the user.
     * @return if valid, the integer given by the user, else -1.
     */
    public int askPositiveInt(String message) {
        int integer = -1;
        display(message);
        try {
            integer = Integer.parseInt(scanInput.nextLine());
        } catch (NumberFormatException e) {
            display("Input is not an integer.");
        }
        return integer;
    }

    /**
     * Asks an action (display, add, delete, update, return, quit) from the user,
     * realised on a object (user, storage, equipment, borrowing).
     * @return an array of string containing actions entered by the user.
     */
    public String[] getAction() {
        String argument;
        String[] arguments = new String[2];
        try {
            do {
                display("Enter an action > ");
                argument = scanInput.nextLine();
                String[] arg = argument.split(" ");
                if (arg.length < 2) {
                    arguments[ACTION] = arg[ACTION];
                    arguments[OBJECT] = "";
                } else if (arg.length == 2) {
                    arguments[ACTION] = arg[ACTION];
                    arguments[OBJECT] = arg[OBJECT];
                } else {
                    for (int i = 0; i < 2; i++) {
                        arguments[i] = "";
                    }
                }

                if (arguments[ACTION].equals(QUIT_ACTION) || arguments[ACTION].equals(RETURN_ACTION)) {
                    return arguments;
                }

            } while (!ACTIONS.contains(arguments[ACTION]) || !OBJECTS.contains(arguments[OBJECT]));

        } catch (ArrayIndexOutOfBoundsException e) {
            display(e.getMessage());
        }

        return arguments;
    }

    /**
     * Displays an object
     * @param object object to print.
     */
    public void display(Object object) {
        System.out.println(object);
    }

    /**
     * Get inputs from the user.
     * @return an array of string containing strings entered by the user.
     */
    public ArrayList<String> getUserInput() {
        String string = scanInput.nextLine();

        ArrayList<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        return list;
    }

    /**
     * Asks an equipment type to the user.
     * @return an
     */
    public int askEquipmentType() {
        display("1 - GameController\n2 - Headset\n3 - Mouse\n4 - Phone\n5 - Tablet\n6 - VRController\n7 - VRHeadset\n8 - Webcam\n9 - Motion Sensor");
        try {
            return Integer.parseInt(scanInput.nextLine());
        } catch (NumberFormatException e) {
            display("Select a number corresponding to an equipment type");
        }
        return -1;
    }

    /**
     * Closes the scanner used to get user input.
     */
    public void closeScanner() {
        scanInput.close();
    }

    /**
     * Print usage depending on the equipment type.
     * @param type type of the equipment for which we want to print the usage.
     */
    public void printEquipmentUsage(int type) {
        String usage = "<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>";
        if (type == GAME_CONTROLLER) {
            usage += " <console>";
        } else if (type == HEADSET) {
            usage += " <microphone : True | False>";
        } else if (type == MOUSE) {
            usage += " <dpi>";
        } else if (type == PHONE) {
            usage += " <sreen size> <OS : ANDROID | IOS | WINDOWS> <number of sim cards>";
        } else if (type == TABLET) {
            usage += " <sreen size> <OS : ANDROID | IOS | WINDOWS> <stylus : True | False>";
        } else if (type == VR_CONTROLLER) {
            usage += " <number of captors>";
        } else if (type == VR_HEADSET) {
            usage += " <refresh rate>";
        } else if (type == WEBCAM) {
            usage += " <resolution>";
        } else if (type == MOTION_SENSOR) {
            usage += " <scope>";
        }
        display(usage);
    }

    /**
     * Asks how the user want to display the data.
     * @param choices list of valid choices.
     * @param message message to print before asking an input from the user.
     * @return the choice of the user.
     */
    public int askDisplayType(List<Integer> choices, String message) {

        int choice = -1;
        display(message);
        try {
            choice = Integer.parseInt(scanInput.nextLine());
            if (choices.contains(choice)) {
                return choice;
            }
        } catch (NumberFormatException e) {
            display("Unrecognised choice.");
        }

        return choice;
    }

    /**
     * Prints a dictionary.
     * @param map the dictionary to print.
     */
    public void printHashMap(HashMap<Integer, ?> map) {
        if (map.size() == 0) {
            display("Empty.");
        }
        for (Map.Entry<Integer, ?> entry : map.entrySet()) {
            display(entry.getValue().getClass().getSimpleName() + " id=" + entry.getKey() + ", " + entry.getValue());
        }
    }

    /**
     * Asks the reason of a borrowing to the user.
     * @return the borrowing reason.
     */
    public BorrowingEntity.BorrowingReason askBorrowingReason() {
        BorrowingEntity.BorrowingReason borrowingReason = null;
        display("reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO");
        try {
            borrowingReason = BorrowingEntity.BorrowingReason.valueOf(scanInput.nextLine());
        } catch (IllegalArgumentException e) {
            display("Unrecognized reason, ");
        }
        return borrowingReason;
    }

    /**
     * Asks the state of an equipment to the user.
     * @return the state of the equipment.
     */
    public EquipmentEntity.State getState() {
        EquipmentEntity.State equipmentState = null;
        display("state: NEW | GOOD | USED | BROKEN");
        try {
            equipmentState = EquipmentEntity.State.valueOf(scanInput.nextLine());
        } catch (IllegalArgumentException e) {
            display("Unrecognized state.");
        }
        return equipmentState;
    }
}
