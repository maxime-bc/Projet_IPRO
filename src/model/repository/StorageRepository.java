package model.repository;

import data.ApplicationData;
import data.Status;
import model.entity.storage.StorageEntity;

import java.util.ArrayList;
import java.util.Map;

import static constants.Constants.*;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

/**
 * Manipulate storage areas stored inside the class ApplicationData.
 * @see ApplicationData
 */
public class StorageRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    /**
     * Add a storage area to the storage areas dictionary stored inside the ApplicationData class.
     * @param inputs list of attributes for the storage area.
     * @return Status, a class containing a code and a message.
     */
    public static Status addStorage(ArrayList<String> inputs) {
        Status status = new Status();

        try {
            int managerId = Integer.parseInt(inputs.get(1));
            String storageArea = inputs.get(0);

            if (appData.getUserEntities().containsKey(managerId)) {

                if (!storageAreaExists(storageArea)) {
                    appData.getStorageEntities().put(appData.getCurrentStorageId(),
                            new StorageEntity(storageArea, managerId));
                    appData.setCurrentStorageId(appData.getCurrentStorageId() + 1);
                    status.setStatus(SUCCESS, ADD);
                } else {
                    status.setStatus(ERROR, AREA_EXISTS);
                }
            } else {
                status.setStatus(ERROR, NONEXISTENT_ID);
            }

        } catch (IndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        } catch (NumberFormatException e) {
            status.setStatus(ERROR, TYPE_ERROR);
        }
        return status;
    }

    /**
     * Delete a storage area from the storage areas dictionary stored inside the ApplicationData class.
     * @param id identifier of the storage area to delete.
     * @return Status, a class containing a code and a message.
     */
    public static Status deleteStorage(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getStorageEntities().containsKey(id)) {
            appData.getStorageEntities().remove(id);
            status.setStatus(SUCCESS, DELETE);
        }
        return status;
    }

    /**
     * Update a storage area from the storage areas dictionary stored inside the ApplicationData class.
     * @param id identifier of the storage area to update.
     * @param inputs list of updated attributes for the storage area.
     * @return Status, a class containing a code and a message.
     */
    public static Status updateStorage(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, UPDATE_ERROR);

        if (appData.getStorageEntities().containsKey(id)) {
            try {
                int managerId = Integer.parseInt(inputs.get(1));

                if (appData.getUserEntities().containsKey(managerId)) {
                    StorageEntity storageEntity = appData.getStorageEntities().get(id);
                    storageEntity.setStorageArea(inputs.get(0));
                    storageEntity.setManagerID(managerId);
                    status.setStatus(SUCCESS, ADD);
                } else {
                    status.setStatus(ERROR, NONEXISTENT_ID);
                }

            } catch (NumberFormatException e) {
                status.setStatus(ERROR, TYPE_ERROR);
            } catch (IndexOutOfBoundsException e) {
                status.setStatus(ERROR, ARGS_ERROR);
            }
        } else {
            status.setStatus(ERROR, AREA_EXISTS);
        }
        return status;
    }

    /**
     * Check if a storage area exists.
     * @param storageArea name of the storage area to check.
     * @return true if it exists, false if it doesn't.
     */
    private static boolean storageAreaExists(String storageArea) {
        for (Map.Entry<Integer, StorageEntity> storageEntityEntry : appData.getStorageEntities().entrySet()) {
            if (storageEntityEntry.getValue().getStorageArea().equals(storageArea)) {
                return true;
            }
        }
        return false;
    }

    public static boolean storageAreaExists(int storageAreaId) {
        for (Map.Entry<Integer, StorageEntity> storageEntityEntry : appData.getStorageEntities().entrySet()) {
            if (storageEntityEntry.getKey() == storageAreaId) {
                return true;
            }
        }
        return false;
    }
}
