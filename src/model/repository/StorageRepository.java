package model.repository;

import data.ApplicationData;
import data.Status;
import model.entity.storage.StorageEntity;

import java.util.ArrayList;

import static constants.Constants.ERROR;
import static constants.Constants.SUCCESS;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

public class StorageRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static Status addStorage(ArrayList<String> inputs) {
        Status status = new Status();

        try {
            appData.getStorageEntities().put(appData.getCurrentStorageId(),
                    new StorageEntity(inputs.get(0),
                            Integer.parseInt(inputs.get(1))));
            appData.setCurrentStorageId(appData.getCurrentStorageId() + 1);

            status.setStatus(SUCCESS, ADD);
        } catch (IndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        } catch (NumberFormatException e) {
            status.setStatus(ERROR, TYPE_ERROR);
        }
        return status;
    }

    public static Status deleteStorage(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getStorageEntities().containsKey(id)) {
            appData.getStorageEntities().remove(id);
            status.setStatus(SUCCESS, DELETE);
        }
        return status;
    }

    public static Status updateStorage(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, UPDATE_ERROR);

        if (appData.getStorageEntities().containsKey(id)) {
            try {
                StorageEntity storageEntity = appData.getStorageEntities().get(id);
                storageEntity.setStorageArea(inputs.get(0));
                storageEntity.setManagerID(Integer.parseInt(inputs.get(1)));
                status.setStatus(SUCCESS, ADD);

            } catch (NumberFormatException e) {
                status.setStatus(ERROR, TYPE_ERROR);
            } catch (IndexOutOfBoundsException e) {
                status.setStatus(ERROR, ARGS_ERROR);
            }
        }
        return status;
    }
}
