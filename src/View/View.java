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
        System.out.println("**     Action : afficher, ajouter, modifier, supprimer   **");
        System.out.println("**                 (ou quitter)                          **");
        System.out.println("**	   Sur quoi ? : lots, clients, commandes, stock, menu  **");
        System.out.println("**     si commande : clients ou boutique ?               **");
        System.out.println("**   exemple : <ajouter> <clients/commandes/...>         **");
        System.out.println("**   exemple : <afficher> <commandes> <boutique/client>  **");
        System.out.println("**   exemple : <quitter>                                 **");
        System.out.println("***********************************************************");
    }

    public String[] getAction() {
        String argument;
        String[] arguments = new String[2];
        try {
            do {
                System.out.print("Que souhaitez-vous ? > ");
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
                    scanInput.close();
                    System.exit(0);
                }
            } while (!actions.contains(arguments[0]) || !objects.contains(arguments[1]));

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        return arguments;
    }

    public void display(Object object){
        System.out.println(object);
    }

}
