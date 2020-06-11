package Controller;

import Model.ApplicationData;
import Model.Entity.*;

import java.util.ArrayList;
import java.util.Date;

public class Controller {

    public Controller(){

        //pour test

        ApplicationData applicationData = ApplicationData.getInstance();

        applicationData.getUserEntities().add(new UserEntity("Bertrand", "Martin", "23 rue test",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        applicationData.getUserEntities().add(new UserEntity("Christophe", "Bertrand", "23 rue lol",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        String file = "app.data";

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

        Serialize.serialize(applicationData, file);
        System.out.println(Serialize.deserialize(file));
    }
}
