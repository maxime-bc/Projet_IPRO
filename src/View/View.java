package View;

import java.util.List;
import java.util.Scanner;

public class View {

    public static final List<String> actions = List.of("display", "add", "update", "delete", "quit");
    public static final List<String> objects = List.of("user", "borrowing", "equipment");
    private final Scanner scanInput;

    public View() {
        printMenu();
        this.scanInput = new Scanner(System.in);
    }

    private void printMenu() {
        System.out.println("***********                MENU                 ***********");
        System.out.println("**   Actions : display, add, update, delete, quit        **");
        System.out.println("**	 On what data type : user, borrowing, equipment      **");
        System.out.println("**   Ex : add user <attributes>                          **");
        System.out.println("**   Ex : display user                                   **");
        System.out.println("**   ...                                                 **");
        System.out.println("***********************************************************");
    }

    public void printUsage(String action) {
        if (action.equals(objects.get(0))) {
            System.out.println("<first_name> <last_name> <address> <phone_number> <email> <user_type : JIN_STUDENT | Y2_STUDENT | ENSIIE | C19 | TEACHER | OTHER>");
        } else if (action.equals(objects.get(1))) {
            System.out.println("<reason : JIN_PROJECT | JIN_UE | Y2_UE | ENSIIE | PERSONAL_WORK | STARTUP | DEMO> " +
                    "<borrowing end: dd/mm/yyyy> <borrowed_equipment_id> <borrower_id>");
        } else if (action.equals(objects.get(2))) {
            System.out.println("<equipment_owner: ENSIIE | TSP | C19 | UEVE> <brand> <purchase_date: dd/mm/yyyy> <purchase_price> <state: NEW | GOOD | USED | BROKEN> <storage_id> <quantity>");
        }
    }

    public int getIdOfElementToDelete() {
        System.out.println("Enter the id of the element you want to delete :");
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
                    arguments[0] = arg[0];
                    arguments[1] = "";
                } else if (arg.length == 2) {
                    arguments[0] = arg[0];
                    arguments[1] = arg[1];
                } else {
                    for (int i = 0; i < 2; i++) {
                        arguments[i] = "";
                    }
                }

                if (arguments[0].equals("quit")) {
                    return arguments;
                }

            } while (!actions.contains(arguments[0]) || !objects.contains(arguments[1]));

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        return arguments;
    }

    public void display(Object object) {
        System.out.println(object);
    }

    public String[] getUserInput() {
        String string = scanInput.nextLine();
        return string.split(" ");
    }

    public void closeScanner() {
        scanInput.close();
    }
}
