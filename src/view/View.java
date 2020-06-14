package view;

import model.entity.equipment.EquipmentEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
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
        System.out.println("******************************* MENU ********************************");
        System.out.println("**   Actions : display, add, update, delete, quit                  **");
        System.out.println("**	 On what data type : user, borrowing, equipment, storage       **");
        System.out.println("**   Ex : add user <attributes>                                    **");
        System.out.println("**   Ex : display user                                             **");
        System.out.println("**   ...                                                           **");
        System.out.println("*********************************************************************");
    }

    public void printAddUsage(String action) {
        switch (action) {
            case USER_OBJECT:
                System.out.println("<first_name> <last_name> <address> <phone_number> <email> <user_type : JIN_STUDENT | Y2_STUDENT | ENSIIE | C19 | TEACHER | OTHER>");
                break;
            case BORROWING_OBJECT:
                System.out.println("<reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO> " +
                        "<borrowing start: dd/mm/yyyy> <borrowing end: dd/mm/yyyy> <borrowed_equipment_id> <borrower_id>");
                break;
            case EQUIPMENT_OBJECT:
                System.out.println("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> <purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
                break;
            case STORAGE_OBJECT:
                System.out.println("<storage_area> <manager_id>");
                break;
        }
    }

    public void printUpdateUsage(String action) {
        switch (action) {
            case USER_OBJECT:
                System.out.println("<first_name> <last_name> <address> <phone_number> <email> <user_type : JIN_STUDENT | Y2_STUDENT | ENSIIE | C19 | TEACHER | OTHER>");
                break;
            case BORROWING_OBJECT:
                System.out.println("<reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO> " +
                        "<borrowing end: dd/mm/yyyy> <borrowed_equipment_id> <borrower_id>");
                break;
            case EQUIPMENT_OBJECT:
                System.out.println("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> <purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
                break;
            case STORAGE_OBJECT:
                System.out.println("<storage_area> <manager_id>");
                break;
        }
    }

    public int getIdOfElement() {
        System.out.println("Id of the element on which you want to perform this action ? > ");
        return Integer.parseInt(scanInput.nextLine());
    }

    public String[] getAction() {
        String argument;
        String[] arguments = new String[2];
        try {
            do {
                System.out.print("Enter an action > ");
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

                if (arguments[ACTION].equals(QUIT_ACTION)) {
                    return arguments;
                }

            } while (!ACTIONS.contains(arguments[ACTION]) || !OBJECTS.contains(arguments[OBJECT]));

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
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

    public int askQuantity() {
        display("Quantity > ");
        String string = scanInput.nextLine();
        return Integer.parseInt(string);
    }

    public int askEquipmentType() {
        boolean valid = false;
        int choice = -1;
        display("1 - GameController\n2 - Headset\n3 - Mouse\n4 - Phone\n5 - Tablet\n6 - VRController\n7 - VRHeadset\n8 - Webcam");
        while (!valid) {
            try {
                choice = Integer.parseInt(scanInput.nextLine());
                if (Arrays.asList(GAME_CONTROLLER, HEADSET, MOUSE, PHONE, TABLET, VR_CONTROLLER, VR_HEADSET, WEBCAM).contains(choice)) {
                    valid = true;
                } else {
                    System.out.println("Unrecognised choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Unrecognised choice.");
            }
        }
        return choice;
    }

    public void printWrongArg(String arg) {
        System.out.println(arg + " is not a valid argument.\n");
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

    public void printEquipments(ArrayList<EquipmentEntity> equipmentEntities){
        if(equipmentEntities.size() == 0){
            display("Empty.");
        }
        for(EquipmentEntity equipmentEntity: equipmentEntities){
             display(equipmentEntity.getClass().getName() + " : " + equipmentEntity);
        }
    }
}
