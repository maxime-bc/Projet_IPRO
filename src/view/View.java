package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.Constants.*;

public class View {

    public static final List<String> actions = List.of("display", "add", "update", "delete", "quit");
    public static final List<String> objects = List.of("user", "borrowing", "equipment", "storage");
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
        if (action.equals(objects.get(USER_OBJECT))) {
            System.out.println("<first_name> <last_name> <address> <phone_number> <email> <user_type : JIN_STUDENT | Y2_STUDENT | ENSIIE | C19 | TEACHER | OTHER>");
        } else if (action.equals(objects.get(BORROWING_OBJECT))) {
            System.out.println("<reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO> " +
                    "<borrowing start: dd/mm/yyyy> <borrowing end: dd/mm/yyyy> <borrowed_equipment_id> <borrower_id>");
        } else if (action.equals(objects.get(EQUIPMENT_OBJECT))) {
            System.out.println("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> <purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
        } else if (action.equals(objects.get(STORAGE_OBJECT))) {
            System.out.println("<storage_area> <manager_id>");
        }
    }

    public void printUpdateUsage(String action) {
        if (action.equals(objects.get(USER_OBJECT))) {
            System.out.println("<first_name> <last_name> <address> <phone_number> <email> <user_type : JIN_STUDENT | Y2_STUDENT | ENSIIE | C19 | TEACHER | OTHER>");
        } else if (action.equals(objects.get(BORROWING_OBJECT))) {
            System.out.println("<reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO> " +
                    "<borrowing end: dd/mm/yyyy> <borrowed_equipment_id> <borrower_id>");
        } else if (action.equals(objects.get(EQUIPMENT_OBJECT))) {
            System.out.println("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> <purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id>");
        } else if (action.equals(objects.get(STORAGE_OBJECT))) {
            System.out.println("<storage_area> <manager_id>");
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

                if (arguments[ACTION].equals(View.actions.get(QUIT_ACTION))) {
                    return arguments;
                }

            } while (!actions.contains(arguments[ACTION]) || !objects.contains(arguments[OBJECT]));

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

    public int askQuantity(){
        display("Quantity > ");
        String string = scanInput.nextLine();
        return Integer.parseInt(string);
    }

    public String askEquipmentType(){
        display("GameController | Headset | Mouse | Phone | Tablet | VRController | VRHeadset | Webcam");
        display("Type > ");
        return scanInput.nextLine();
    }

    public void printWrongArg(String arg) {
        System.out.println(arg + " is not a valid argument.\n");
    }

    public void closeScanner() {
        scanInput.close();
    }
}
