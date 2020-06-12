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
        if (inputs.size() != 2) {
            status.setStatus(ERROR, ARGS_ERROR);
            return status;
        }
        try {
            appData.getStorageEntities().add(new StorageEntity(appData.getCurrentStorageId(), inputs.get(0),
                    Integer.parseInt(inputs.get(1))));
            appData.setCurrentStorageId(appData.getCurrentStorageId() + 1);

            status.setStatus(SUCCESS, ADD);
        } catch (IllegalArgumentException e) {
            status.setStatus(ERROR, ADD_ERROR);
        }
        return status;
    }

    public static Status deleteStorage(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        for (StorageEntity storage : appData.getStorageEntities()) {
            if (storage.getId() == id) {
                appData.getStorageEntities().remove(storage);
                status.setStatus(SUCCESS, DELETE);
                break;
            }
        }
        return status;
    }

    public static Status updateStorage(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, UPDATE_ERROR);
        if (inputs.size() != 2) {
            status.setStatus(ERROR, ARGS_ERROR);
            return status;
        }

        for (StorageEntity storage : appData.getStorageEntities()) {

            if (storage.getId() == id) {
                storage.setStorageArea(inputs.get(0));
                storage.setManagerID(Integer.parseInt(inputs.get(1)));
                status.setStatus(SUCCESS, ADD);

                break;
            }
        }
        return status;
    }
}
