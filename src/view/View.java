package view;

import model.entity.borrowing.BorrowingEntity;
import model.entity.equipment.EquipmentEntity;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.Constants.*;

public class View {
    private final Scanner scanInput;

    public View() {
        printMenu();
        this.scanInput = new Scanner(System.in);
    }

    private void printMenu() {
        display("******************************* MENU ********************************");
        display("**   Actions : display, add, update, delete, return, quit          **");
        display("**	 On what data type : user, borrowing, equipment, storage       **");
        display("**   Ex : add user <attributes>                                    **");
        display("**   Ex : display user                                             **");
        display("**   ...                                                           **");
        display("*********************************************************************");
    }

    public void printAddUsage(String action) {
        switch (action) {
            case USER_OBJECT:
                display("<first_name> <last_name> <address> <phone_number> <email> <user_type : JIN_STUDENT | Y2_STUDENT | ENSIIE | C19 | TEACHER | OTHER>");
                break;
            case BORROWING_OBJECT:
                display("<reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO> " +
                        "<borrowing end: dd/mm/yyyy> <borrowed_equipment_id> <borrower_id>");
                break;
            case EQUIPMENT_OBJECT:
                display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> <purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
                break;
            case STORAGE_OBJECT:
                display("<storage_area> <manager_id>");
                break;
        }
    }

    public int askInt(String message) {
        int integer = -1;
        display(message);
        try {
            integer = Integer.parseInt(scanInput.nextLine());
        } catch (NumberFormatException e) {
            display("Input is not an integer.");
        }
        return integer;
    }

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

    public void display(Object object) {
        System.out.println(object);
    }

    public ArrayList<String> getUserInput() {
        String string = scanInput.nextLine();

        ArrayList<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        return list;
    }

    public int askEquipmentType() {
        int choice = -1;
        display("1 - GameController\n2 - Headset\n3 - Mouse\n4 - Phone\n5 - Tablet\n6 - VRController\n7 - VRHeadset\n8 - Webcam");
        try {
            choice = Integer.parseInt(scanInput.nextLine());
            if (Arrays.asList(GAME_CONTROLLER, HEADSET, MOUSE, PHONE, TABLET, VR_CONTROLLER, VR_HEADSET, WEBCAM).contains(choice)) {
                return choice;
            }
        } catch (NumberFormatException e) {
            display("Unrecognised choice.");
        }
        return choice;
    }

    public void closeScanner() {
        scanInput.close();
    }

    public void printUsage(int type) {
        if (type == GAME_CONTROLLER) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
        } else if (type == HEADSET) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
        } else if (type == MOUSE) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
        } else if (type == PHONE) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id> <screen_size> <operating_system : IOS | ANDROID | WINDOWS>");
        } else if (type == TABLET) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id> <screen_size> <operating_system : IOS | ANDROID | WINDOWS>");
        } else if (type == VR_CONTROLLER) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
        } else if (type == VR_HEADSET) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
        } else if (type == WEBCAM) {
            display("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> " +
                    "<purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id> <resolution>");
        }
    }

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

    public void printHashMap(HashMap<Integer, ?> map) {
        if (map.size() == 0) {
            display("Empty.");
        }
        for (Map.Entry<Integer, ?> entry : map.entrySet()) {
            display(entry.getValue().getClass().getSimpleName() + " id=" + entry.getKey() + ", " + entry.getValue());
        }
        display("");
    }

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
