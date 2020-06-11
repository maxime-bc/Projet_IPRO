package Controller;

import Model.ApplicationData;
import Model.Entity.UserEntity;
import View.View;

public class Controller {

    private static final String APP_DATA_FILE = "app.data";
    private final View view;
    private final ApplicationData applicationData = ApplicationData.getInstance();

    public Controller() {

        applicationData.getUserEntities().add(new UserEntity("Bertrand", "Martin", "23 rue test",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        view = new View();
        run();

        //pour test

/*        applicationData.getUserEntities().add(new UserEntity("Bertrand", "Martin", "23 rue test",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        applicationData.getUserEntities().add(new UserEntity("Christophe", "Bertrand", "23 rue lol",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));



/*        ArrayList<UserEntity> userEntities = new ArrayList<>();

        userEntities.add(new UserEntity("Bertrand", "Martin", "23 rue test",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        userEntities.add(new UserEntity("Christophe", "Bertrand", "23 rue lol",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        PhoneEntity phoneEntity = new PhoneEntity(EquipmentEntity.Owner.C19, "b", new Date(),
                0.1, EquipmentEntity.State.GOOD, false, -1, 1, 5.4,
                PortableDeviceEntity.OperatingSystem.ANDROID);

        PhoneEntity phoneEntity2 = new PhoneEntity(EquipmentEntity.Owner.C19, "b", new Date(),
                0.1, EquipmentEntity.State.GOOD, false, -1, 1, 5.4,
                PortableDeviceEntity.OperatingSystem.ANDROID);*/

/*        Serialize.serialize(applicationData, APP_DATA_FILE);
        System.out.println(Serialize.deserialize(APP_DATA_FILE));*/
    }

    private void run() {
        boolean quit = false;
        while (!quit) {
            quit = this.executeAction(this.view.getAction());
        }
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

        } else return arguments[0].equals("quitter");

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

    }

    private void update(String[] arguments) {

    }

    private void delete(String[] arguments) {

    }
}
