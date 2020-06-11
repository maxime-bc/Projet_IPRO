import Model.Entity.EquipmentEntity;
import Model.Entity.PhoneEntity;
import Model.Entity.PortableDeviceEntity;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        PhoneEntity phoneEntity = new PhoneEntity(EquipmentEntity.Owner.C19, "b", new Date(),
                0.1, EquipmentEntity.State.GOOD, false, -1, 1, 5.4,
                PortableDeviceEntity.OperatingSystem.ANDROID);

        PhoneEntity phoneEntity2 = new PhoneEntity(EquipmentEntity.Owner.C19, "b", new Date(),
                0.1, EquipmentEntity.State.GOOD, false, -1, 1, 5.4,
                PortableDeviceEntity.OperatingSystem.ANDROID);

        System.out.println(phoneEntity);
    }
}
