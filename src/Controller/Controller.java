package Controller;

import Model.Entity.EquipmentEntity;
import Model.Entity.PhoneEntity;
import Model.Entity.PortableDeviceEntity;
import Model.Entity.UserEntity;

import java.util.ArrayList;
import java.util.Date;

public class Controller {

    public Controller(){

        //pour test

        String file = "MyData.ser";

        ArrayList<UserEntity> userEntities = new ArrayList<>();

        userEntities.add(new UserEntity("Bertrand", "Martin", "23 rue test",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        userEntities.add(new UserEntity("Christophe", "Bertrand", "23 rue lol",
                "01.02.2", "test@test", UserEntity.UserType.OTHER));

        PhoneEntity phoneEntity = new PhoneEntity(EquipmentEntity.Owner.C19, "b", new Date(),
                0.1, EquipmentEntity.State.GOOD, false, -1, 1, 5.4,
                PortableDeviceEntity.OperatingSystem.ANDROID);

        PhoneEntity phoneEntity2 = new PhoneEntity(EquipmentEntity.Owner.C19, "b", new Date(),
                0.1, EquipmentEntity.State.GOOD, false, -1, 1, 5.4,
                PortableDeviceEntity.OperatingSystem.ANDROID);

        Serialize.serialize(userEntities, file);
        System.out.println(Serialize.deserialize());
    }
}
