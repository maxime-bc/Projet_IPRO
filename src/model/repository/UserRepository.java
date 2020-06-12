package model.repository;

import data.ApplicationData;
import data.Status;
import model.entity.user.UserEntity;

import java.util.ArrayList;

import static constants.Constants.ERROR;
import static constants.Constants.SUCCESS;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

public class UserRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    //TODO : check user inputs
    public static Status addUser(ArrayList<String> inputs) {
        Status status = new Status();
        if (inputs.size() != 6) {
            status.setStatus(ERROR, ARGS_ERROR);
        }

        try {
            appData.getUserEntities().add(new UserEntity(appData.getCurrentUserId(), inputs.get(0), inputs.get(1),
                    inputs.get(2), inputs.get(3), inputs.get(4), UserEntity.UserType.valueOf(inputs.get(5))));
            appData.setCurrentUserId(appData.getCurrentUserId() + 1);

            status.setStatus(SUCCESS, ADD);
        } catch (IllegalArgumentException e) {
            status.setStatus(ERROR, ADD_ERROR);
        }
        return status;
    }

    public static Status deleteUser(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        for (UserEntity user : appData.getUserEntities()) {
            if (user.getId() == id) {
                appData.getUserEntities().remove(user);
                status.setStatus(SUCCESS, DELETE);
                break;
            }
        }
        return status;
    }

    public static Status updateUser(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, UPDATE_ERROR);

        for (UserEntity user : appData.getUserEntities()) {
            if (user.getId() == id) {
                user.setFirstName(inputs.get(0));
                user.setLastName(inputs.get(1));
                user.setAddress(inputs.get(2));
                user.setPhoneNumber(inputs.get(3));
                user.setEmail(inputs.get(4));
                user.setUserType(UserEntity.UserType.valueOf(inputs.get(5)));

                status.setStatus(SUCCESS, UPDATE);
                break;
            }
        }
        return status;
    }
}
