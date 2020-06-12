package model.repository;

import constants.ErrorMessages;
import constants.SuccessMessages;
import model.ApplicationData;
import model.entity.UserEntity;

import java.util.ArrayList;

public class UserRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    //TODO : check user inputs
    public static String addUser(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 6) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            appData.getUserEntities().add(new UserEntity(appData.getCurrentUserId(), inputs.get(0), inputs.get(1),
                    inputs.get(2), inputs.get(3), inputs.get(4), UserEntity.UserType.valueOf(inputs.get(5))));
            appData.setCurrentUserId(appData.getCurrentUserId() + 1);

            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }

    public static String deleteUser(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (UserEntity user : appData.getUserEntities()) {
            if (user.getId() == id) {
                appData.getUserEntities().remove(user);
                message = SuccessMessages.DELETE;
                break;
            }
        }
        return message;
    }

    public static String updateUser(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (UserEntity user : appData.getUserEntities()) {
            if (user.getId() == id) {
                user.setFirstName(inputs.get(0));
                user.setLastName(inputs.get(1));
                user.setAddress(inputs.get(2));
                user.setPhoneNumber(inputs.get(3));
                user.setEmail(inputs.get(4));
                user.setUserType(UserEntity.UserType.valueOf(inputs.get(5)));

                message = SuccessMessages.UPDATE;
                break;
            }
        }
        return message;
    }
}
