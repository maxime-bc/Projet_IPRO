package model.repository;

import constants.Constants;
import data.ApplicationData;
import data.Status;
import model.entity.equipment.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static constants.Constants.*;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

public class EquipmentRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static Status addEquipment(ArrayList<String> inputs, int type) {
        Status status = new Status();

        try {
            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
            int storageAreaId = Integer.parseInt(inputs.get(5));

            if (appData.getStorageEntities().containsKey(storageAreaId)) {


                if (type == GAME_CONTROLLER) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                            new GameControllerEntity(EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1),
                                    format.parse(inputs.get(2)), Double.parseDouble(inputs.get(3)),
                                    EquipmentEntity.State.valueOf(inputs.get(4)), false,
                                    Integer.parseInt(inputs.get(5))));
                } else if (type == HEADSET) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                            new HeadsetEntity(EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1),
                                    format.parse(inputs.get(2)), Double.parseDouble(inputs.get(3)),
                                    EquipmentEntity.State.valueOf(inputs.get(4)), false,
                                    Integer.parseInt(inputs.get(5))));
                } else if (type == MOUSE) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new MouseEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5))));
                } else if (type == PHONE) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new PhoneEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Double.parseDouble(inputs.get(6)),
                            PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7))));
                } else if (type == TABLET) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new TabletEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Double.parseDouble(inputs.get(6)),
                            PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7))));
                } else if (type == VR_CONTROLLER) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new VRControllerEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5))));
                } else if (type == VR_HEADSET) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new VRHeadsetEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5))));
                } else if (type == WEBCAM) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new WebcamEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), inputs.get(6)));
                }
                appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);
                status.setStatus(SUCCESS, ADD);

            } else {
                status.setStatus(ERROR, NONEXISTENT_ID);
            }
        } catch (IllegalArgumentException | ParseException e) {
            status.setStatus(ERROR, ADD_ERROR);
        } catch (IndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        }
        return status;
    }

    public static Status deleteEquipment(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getEquipmentEntities().containsKey(id)) {
            appData.getEquipmentEntities().remove(id);
            status.setStatus(SUCCESS, DELETE);
        }
        return status;
    }

    public static Status updateEquipment(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getEquipmentEntities().containsKey(id)) {
            EquipmentEntity equipmentEntity = appData.getEquipmentEntities().get(id);
            try {
                int storageAreaId = Integer.parseInt(inputs.get(5));

                if (appData.getStorageEntities().containsKey(storageAreaId)) {
                    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                    equipmentEntity.setOwner(EquipmentEntity.Owner.valueOf(inputs.get(0)));
                    equipmentEntity.setBrand(inputs.get(1));
                    equipmentEntity.setPurchaseDate(format.parse(inputs.get(2)));
                    equipmentEntity.setPurchasePrice(Double.parseDouble(inputs.get(3)));
                    equipmentEntity.setState(EquipmentEntity.State.valueOf(inputs.get(4)));
                    equipmentEntity.setStorageId(storageAreaId);
                    status.setStatus(SUCCESS, UPDATE);

                } else {
                    status.setStatus(ERROR, NONEXISTENT_ID);
                }
            } catch (ParseException e) {
                status.setStatus(ERROR, TYPE_ERROR);
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                status.setStatus(ERROR, ARGS_ERROR);
            }
        }
        return status;
    }
}
