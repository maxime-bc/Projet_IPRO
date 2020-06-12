package model.repository;

import constants.ErrorMessages;
import constants.SuccessMessages;
import model.ApplicationData;
import model.entity.StorageEntity;

import java.util.ArrayList;

public class StorageRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static String addStorage(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 2) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            appData.getStorageEntities().add(new StorageEntity(appData.getCurrentStorageId(), inputs.get(0),
                    Integer.parseInt(inputs.get(1))));
            appData.setCurrentStorageId(appData.getCurrentStorageId() + 1);
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public static String deleteStorage(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (StorageEntity storage : appData.getStorageEntities()) {
            if (storage.getId() == id) {
                appData.getStorageEntities().remove(storage);
                message = SuccessMessages.DELETE;
                break;
            }
        }
        return message;
    }

    public static String updateStorage(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (StorageEntity storage : appData.getStorageEntities()) {

            if (storage.getId() == id) {
                storage.setStorageArea(inputs.get(0));
                storage.setManagerID(Integer.parseInt(inputs.get(1)));
                message = SuccessMessages.UPDATE;

                break;
            }
        }
        return message;
    }
}
