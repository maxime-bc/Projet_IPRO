package model.repository;

import constants.Constants;
import data.ApplicationData;
import data.Status;
import model.entity.equipment.*;
import model.entity.storage.StorageEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static constants.Constants.*;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

/**
 * Manipulate equipments stored inside the class ApplicationData.
 *
 * @see ApplicationData
 */
public class EquipmentRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    /**
     * Add an equipment to the equipments dictionary stored inside the class ApplicationData.
     *
     * @param inputs list of attributes for the equipment.
     * @param type   type of the equipment to be added.
     * @return Status, a class containing a code and a message.
     */
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
                                    Integer.parseInt(inputs.get(5)), inputs.get(6))); //inputs.get(6) == console
                } else if (type == HEADSET) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(),
                            new HeadsetEntity(EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1),
                                    format.parse(inputs.get(2)), Double.parseDouble(inputs.get(3)),
                                    EquipmentEntity.State.valueOf(inputs.get(4)), false,
                                    Integer.parseInt(inputs.get(5)), Boolean.parseBoolean(inputs.get(6))));
                } else if (type == MOUSE) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new MouseEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Float.parseFloat(inputs.get(6))));
                } else if (type == PHONE) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new PhoneEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Double.parseDouble(inputs.get(6)),
                            PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7)), Integer.parseInt(inputs.get(8))));
                } else if (type == TABLET) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new TabletEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Double.parseDouble(inputs.get(6)),
                            PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7)), Boolean.parseBoolean(inputs.get(8))));
                } else if (type == VR_CONTROLLER) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new VRControllerEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Integer.parseInt(inputs.get(6))));
                } else if (type == VR_HEADSET) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new VRHeadsetEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Integer.parseInt(inputs.get(6))));
                } else if (type == WEBCAM) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new WebcamEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), inputs.get(6)));
                } else if (type == MOTION_SENSOR) {
                    appData.getEquipmentEntities().put(appData.getCurrentEquipmentId(), new MotionSensorEntity(
                            EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                            Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                            Integer.parseInt(inputs.get(5)), Float.parseFloat(inputs.get(6))));
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

    /**
     * Delete an equipment from the equipments dictionary stored inside the class ApplicationData.
     *
     * @param id identifier of the equipment to delete.
     * @return Status, a class containing a code and a message.
     */
    public static Status deleteEquipment(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getEquipmentEntities().containsKey(id)) {
            appData.getEquipmentEntities().remove(id);
            status.setStatus(SUCCESS, DELETE);
        }
        return status;
    }

    /**
     * Update an equipment from the equipments dictionary stored inside the class ApplicationData.
     *
     * @param id     identifier of the equipment to update.
     * @param inputs list of updated attributes for the equipment.
     * @return Status, a class containing a code and a message.
     */
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
                    if (equipmentEntity instanceof GameControllerEntity) {
                        ((GameControllerEntity) equipmentEntity).setConsole(inputs.get(6));
                    } else if (equipmentEntity instanceof HeadsetEntity) {
                        ((HeadsetEntity) equipmentEntity).setWithMicrophone(Boolean.parseBoolean(inputs.get(6)));
                    } else if (equipmentEntity instanceof MotionSensorEntity) {
                        ((MotionSensorEntity) equipmentEntity).setScope(Integer.parseInt(inputs.get(6)));
                    } else if (equipmentEntity instanceof MouseEntity) {
                        ((MouseEntity) equipmentEntity).setDpi(Float.parseFloat(inputs.get(6)));
                    } else if (equipmentEntity instanceof PhoneEntity) {
                        ((PhoneEntity) equipmentEntity).setScreenSize(Double.parseDouble(inputs.get(6)));
                        ((PhoneEntity) equipmentEntity).setOperatingSystem(PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7)));
                        ((PhoneEntity) equipmentEntity).setNbSimCard(Integer.parseInt(inputs.get(8)));
                    } else if (equipmentEntity instanceof TabletEntity) {
                        ((TabletEntity) equipmentEntity).setScreenSize(Double.parseDouble(inputs.get(6)));
                        ((TabletEntity) equipmentEntity).setOperatingSystem(PortableDeviceEntity.OperatingSystem.valueOf(inputs.get(7)));
                        ((TabletEntity) equipmentEntity).setWithStylus(Boolean.parseBoolean(inputs.get(8)));
                    } else if (equipmentEntity instanceof VRControllerEntity) {
                        ((VRControllerEntity) equipmentEntity).setNbCaptors(Integer.parseInt(inputs.get(6)));
                    } else if (equipmentEntity instanceof VRHeadsetEntity) {
                        ((VRHeadsetEntity) equipmentEntity).setRefreshRate(Integer.parseInt(inputs.get(6)));
                    } else if (equipmentEntity instanceof WebcamEntity) {
                        ((WebcamEntity) equipmentEntity).setResolution(inputs.get(6));
                    }
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

    /**
     * Create a dictionary of available equipments.
     *
     * @return a dictionary of available equipments.
     */
    public static HashMap<Integer, EquipmentEntity> getAvailableEquipment() {
        HashMap<Integer, EquipmentEntity> availableEquipments = new HashMap<>();

        for (Map.Entry<Integer, EquipmentEntity> entry : appData.getEquipmentEntities().entrySet()) {
            int key = entry.getKey();
            EquipmentEntity equipmentEntity = entry.getValue();

            if (!equipmentEntity.isBorrowed() && (equipmentEntity.getState() != EquipmentEntity.State.BROKEN)) {
                availableEquipments.put(key, equipmentEntity);
            }
        }

        return availableEquipments;
    }

    /**
     * Create a dictionary of borrowed equipments.
     *
     * @return a dictionary of borrowed equipments.
     */
    public static HashMap<Integer, EquipmentEntity> getBorrowedEquipment() {
        HashMap<Integer, EquipmentEntity> borrowedEquipments = new HashMap<>();

        for (Map.Entry<Integer, EquipmentEntity> entry : appData.getEquipmentEntities().entrySet()) {
            int key = entry.getKey();
            EquipmentEntity equipmentEntity = entry.getValue();

            if (equipmentEntity.isBorrowed() && (equipmentEntity.getState() != EquipmentEntity.State.BROKEN)) {
                borrowedEquipments.put(key, equipmentEntity);
            }
        }

        return borrowedEquipments;
    }

    /**
     * Create a dictionary of equipments filtered by their type.
     *
     * @param type type of equipments to be filered.
     * @return a dictionary of equipments with the same type.
     */
    public static HashMap<Integer, EquipmentEntity> getEquipment(int type) {
        HashMap<Integer, EquipmentEntity> equipments = new HashMap<>();


        for (Map.Entry<Integer, EquipmentEntity> entry : appData.getEquipmentEntities().entrySet()) {
            int key = entry.getKey();
            if (type == GAME_CONTROLLER && entry.getValue() instanceof GameControllerEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == HEADSET && entry.getValue() instanceof HeadsetEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == MOUSE && entry.getValue() instanceof MouseEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == PHONE && entry.getValue() instanceof PhoneEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == TABLET && entry.getValue() instanceof TabletEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == VR_CONTROLLER && entry.getValue() instanceof VRControllerEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == VR_HEADSET && entry.getValue() instanceof VRHeadsetEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == WEBCAM && entry.getValue() instanceof WebcamEntity) {
                equipments.put(key, entry.getValue());
            } else if (type == MOTION_SENSOR && entry.getValue() instanceof MotionSensorEntity) {
                equipments.put(key, entry.getValue());
            }
        }
        return equipments;
    }

    /**
     * Get the state of a equipment.
     *
     * @param borrowingId identifier of the equipment.
     * @return the state of the equipment.
     */
    public static EquipmentEntity.State getEquipmentState(int borrowingId) {
        return appData.getEquipmentEntities().get(borrowingId).getState();
    }

    public static String getEquipmentName(int borrowingId) {
        return appData.getEquipmentEntities().get(borrowingId).getBrand();
    }

    public static int getEquipmentTypeById(int id) {
        EquipmentEntity equipmentEntity = appData.getEquipmentEntities().get(id);

        if (equipmentEntity instanceof GameControllerEntity) {
            return GAME_CONTROLLER;
        } else if (equipmentEntity instanceof HeadsetEntity) {
            return HEADSET;
        } else if (equipmentEntity instanceof MotionSensorEntity) {
            return MOTION_SENSOR;
        } else if (equipmentEntity instanceof MouseEntity) {
            return MOUSE;
        } else if (equipmentEntity instanceof PhoneEntity) {
            return PHONE;
        } else if (equipmentEntity instanceof TabletEntity) {
            return TABLET;
        } else if (equipmentEntity instanceof VRControllerEntity) {
            return VR_CONTROLLER;
        } else if (equipmentEntity instanceof VRHeadsetEntity) {
            return VR_HEADSET;
        } else if (equipmentEntity instanceof WebcamEntity) {
            return WEBCAM;
        }
        return -1;
    }

    public static HashMap<Integer, EquipmentEntity> getEquipmentByStorageAreaId(int storageAreaId) {
        HashMap<Integer, EquipmentEntity> equipments = new HashMap<>();

        for (Map.Entry<Integer, EquipmentEntity> equipmentEntityEntry : appData.getEquipmentEntities().entrySet()) {
            if (equipmentEntityEntry.getValue().getStorageID() == storageAreaId) {
                equipments.put(equipmentEntityEntry.getKey(), equipmentEntityEntry.getValue());
            }
        }
        return equipments;
    }
}
