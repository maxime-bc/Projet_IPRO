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

            if (type == GAME_CONTROLLER) {
                appData.getEquipmentEntities().add(new GameControllerEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5))));
            } else if (type == HEADSET) {
                appData.getEquipmentEntities().add(new HeadsetEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5))));
            } else if (type == MOUSE) {
                appData.getEquipmentEntities().add(new MouseEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5))));
            } else if (type == PHONE) {
                appData.getEquipmentEntities().add(new PhoneEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5)), Double.parseDouble(inputs.get(6)), PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7))));
            } else if (type == TABLET) {
                appData.getEquipmentEntities().add(new TabletEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5)), Double.parseDouble(inputs.get(6)), PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7))));
            } else if (type == VR_CONTROLLER) {
                appData.getEquipmentEntities().add(new VRControllerEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5))));
            } else if (type == VR_HEADSET) {
                appData.getEquipmentEntities().add(new VRHeadsetEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5))));
            } else if (type == WEBCAM) {
                appData.getEquipmentEntities().add(new WebcamEntity(appData.getCurrentEquipmentId(),
                        EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                        Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                        Integer.parseInt(inputs.get(5)), inputs.get(6)));
            }
            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);

            status.setStatus(SUCCESS, ADD);
        } catch (IllegalArgumentException | ParseException e) {
            status.setStatus(ERROR, ADD_ERROR);
        } catch (IndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        }
        return status;
    }

    public static Status deleteEquipment(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        for (EquipmentEntity equipment : appData.getEquipmentEntities()) {
            if (equipment.getId() == id) {
                appData.getEquipmentEntities().remove(equipment);
                status.setStatus(SUCCESS, DELETE);
                break;
            }
        }
        return status;
    }

    public static Status updateEquipment(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, UPDATE_ERROR);
        if (inputs.size() != 6) {
            status.setStatus(ERROR, ARGS_ERROR);
            return status;
        }

        for (EquipmentEntity equipment : appData.getEquipmentEntities()) {

            if (equipment.getId() == id) {
                try {
                    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                    equipment.setOwner(EquipmentEntity.Owner.valueOf(inputs.get(0)));
                    equipment.setBrand(inputs.get(1));
                    equipment.setPurchaseDate(format.parse(inputs.get(2)));
                    equipment.setPurchasePrice(Double.parseDouble(inputs.get(3)));
                    equipment.setState(EquipmentEntity.State.valueOf(inputs.get(4)));
                    equipment.setStorageId(Integer.parseInt(inputs.get(5)));

                    status.setStatus(SUCCESS, UPDATE);

                } catch (ParseException e) {
                    status.setStatus(ERROR, TYPE_ERROR);
                }
                break;
            }
        }
        return status;
    }
}
