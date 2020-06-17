package test;

import constants.Constants;
import data.ApplicationData;
import model.entity.equipment.*;
import model.entity.storage.StorageEntity;
import model.entity.user.UserEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Used to automatically insert data for tests purposes.
 */
public class Test {
    private static final ApplicationData appData = ApplicationData.getInstance();

    //This class adds test data to the application

    public Test() {

        // Add users
        appData.getUserEntities().put(appData.getCurrentUserId(),
                new UserEntity("Maxime", "Blanchon", "Adresse", "01.01",
                        "maxime@test.gmail", UserEntity.UserType.ENSIIE));
        appData.setCurrentUserId(appData.getCurrentUserId() + 1);
        appData.getUserEntities().put(appData.getCurrentUserId(),
                new UserEntity("Pierre", "Bruot", "Adresse", "02.02",
                        "pierre@test.gmail", UserEntity.UserType.ENSIIE));
        appData.setCurrentUserId(appData.getCurrentUserId() + 1);
        appData.getUserEntities().put(appData.getCurrentUserId(),
                new UserEntity("Je suis", "le responsable",
                        "resp", "090929092", "res@onsable.com", UserEntity.UserType.TEACHER));
        appData.setCurrentUserId(appData.getCurrentUserId() + 1);

        // Add storage areas
        appData.getStorageEntities().put(appData.getCurrentStorageId(),
                new StorageEntity("Salle 251", 2));
        appData.setCurrentStorageId(appData.getCurrentStorageId() + 1);
        appData.getStorageEntities().put(appData.getCurrentStorageId(),
                new StorageEntity("Salle 1", 2));
        appData.setCurrentStorageId(appData.getCurrentStorageId() + 1);

        // Add equipments
        try {
            appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                    new PhoneEntity(EquipmentEntity.Owner.ENSIIE, "Samsung Galaxy S10",
                            new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE).parse("21/12/2019"),
                            700, EquipmentEntity.State.NEW, false, 0, 5.8,
                            PortableDeviceEntity.OperatingSystem.ANDROID, 2));
            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);

            appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                    new PhoneEntity(EquipmentEntity.Owner.ENSIIE, "Iphone 11",
                            new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE).parse("27/07/2019"),
                            900, EquipmentEntity.State.NEW, false, 0, 6.1,
                            PortableDeviceEntity.OperatingSystem.IOS, 1));
            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);

            appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                    new WebcamEntity(EquipmentEntity.Owner.ENSIIE, "Logitech c920",
                            new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE).parse("04/05/2016"),
                            700, EquipmentEntity.State.GOOD, false, 0, "720p"));
            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);

            appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                    new VRHeadsetEntity(EquipmentEntity.Owner.ENSIIE, "HTC Vive",
                            new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE).parse("13/09/2017"),
                            700, EquipmentEntity.State.GOOD, false, 1, 90));
            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);

            appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                    new TabletEntity(EquipmentEntity.Owner.ENSIIE, "Apple Ipad",
                            new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE).parse("01/07/2015"),
                            700, EquipmentEntity.State.BROKEN, false, 1, 10.2,
                            PortableDeviceEntity.OperatingSystem.IOS, true));
            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
