package model.repository;

import data.ApplicationData;
import data.Status;
import model.entity.storage.StorageEntity;
import model.entity.user.UserEntity;

import java.util.ArrayList;
import java.util.Map;

import static constants.Constants.ERROR;
import static constants.Constants.SUCCESS;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

/**
 * Manipulate users stored inside the class ApplicationData.
 *
 * @see ApplicationData
 */
public class UserRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    /**
     * Add an user to the users dictionary stored inside the ApplicationData class.
     *
     * @param inputs list of attributes for the user.
     * @return Status, a class containing a code and a message.
     */
    public static Status addUser(ArrayList<String> inputs) {
        Status status = new Status();

        try {
            appData.getUserEntities().put(appData.getCurrentUserId(), new UserEntity(inputs.get(0), inputs.get(1),
                    inputs.get(2), inputs.get(3), inputs.get(4), UserEntity.UserType.valueOf(inputs.get(5))));
            appData.setCurrentUserId(appData.getCurrentUserId() + 1);
            status.setStatus(SUCCESS, ADD);

        } catch (IllegalArgumentException e) {
            status.setStatus(ERROR, TYPE_ERROR);
        } catch (IndexOutOfBoundsException e) {
            status.setStatus(ERROR, ARGS_ERROR);
        }
        return status;
    }

    /**
     * Delete an user from the users dictionary stored inside the ApplicationData class.
     *
     * @param id identifier of the user to delete.
     * @return Status, a class containing a code and a message.
     */
    public static Status deleteUser(int id) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getUserEntities().containsKey(id)) {
            appData.getUserEntities().remove(id);
            status.setStatus(SUCCESS, DELETE);
        }
        return status;
    }

    /**
     * Update an user from the users dictionary stored inside the ApplicationData class.
     *
     * @param id     identifier of the user to update.
     * @param inputs list of updated attributes for the user.
     * @return Status, a class containing a code and a message.
     */
    public static Status updateUser(int id, ArrayList<String> inputs) {
        Status status = new Status(ERROR, NONEXISTENT_ID);

        if (appData.getUserEntities().containsKey(id)) {
            UserEntity userEntity = appData.getUserEntities().get(id);

            try {
                // avoid update when args are not valid.
                String firstName = inputs.get(0),
                        lastName = inputs.get(1),
                        address = inputs.get(2),
                        phoneNumber = inputs.get(3),
                        email = inputs.get(4);

                UserEntity.UserType userType = UserEntity.UserType.valueOf(inputs.get(5));

                userEntity.setFirstName(firstName);
                userEntity.setLastName(lastName);
                userEntity.setAddress(address);
                userEntity.setPhoneNumber(phoneNumber);
                userEntity.setEmail(email);
                userEntity.setUserType(userType);
                status.setStatus(SUCCESS, UPDATE);

            } catch (IllegalArgumentException e) {
                status.setStatus(ERROR, TYPE_ERROR);
            } catch (IndexOutOfBoundsException e) {
                status.setStatus(ERROR, ARGS_ERROR);
            }
        }
        return status;
    }

    public static boolean userExists(int userId) {
        for (Map.Entry<Integer, UserEntity> userEntityEntry : appData.getUserEntities().entrySet()) {
            if (userEntityEntry.getKey() == userId) {
                return true;
            }
        }
        return false;
    }
}
