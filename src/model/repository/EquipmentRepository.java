package model.repository;

import constants.Constants;
import constants.ErrorMessages;
import constants.SuccessMessages;
import model.ApplicationData;
import model.entity.EquipmentEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EquipmentRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static String addEquipment(ArrayList<String> inputs) {
        String message;
        if (inputs.size() != 7) {
            return ErrorMessages.ARGS_ERROR;
        }

        try {
            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);

            appData.getEquipmentEntities().add(new EquipmentEntity(appData.getCurrentEquipmentId(),
                    EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                    Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                    Integer.parseInt(inputs.get(5)), Integer.parseInt(inputs.get(6))));

            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);
            message = SuccessMessages.ADD;
        } catch (IllegalArgumentException | ParseException e) {
            message = ErrorMessages.ADD_ERROR;
        }
        return message;
    }


    public static String deleteEquipment(int id) {
        String message = ErrorMessages.NONEXISTENT_ID;

        for (EquipmentEntity equipment : appData.getEquipmentEntities()) {
            if (equipment.getId() == id) {
                int quantity = equipment.getQuantity();

                if (quantity > 1) {
                    equipment.setQuantity(quantity - 1);
                    message = SuccessMessages.DELETE;

                } else {
                    // Only one equipment
                    appData.getEquipmentEntities().remove(equipment);
                    message = SuccessMessages.DELETE;
                    break;
                }
            }
        }
        return message;
    }

    public static String updateEquipment(int id, ArrayList<String> inputs) {
        String message = ErrorMessages.UPDATE_ERROR;

        for (EquipmentEntity equipment : appData.getEquipmentEntities()) {

            if (equipment.getId() == id) {
                try{
                    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);
                    equipment.setOwner(EquipmentEntity.Owner.valueOf(inputs.get(0)));
                    equipment.setBrand(inputs.get(1));
                    equipment.setPurchaseDate(format.parse(inputs.get(2)));
                    equipment.setPurchasePrice(Double.parseDouble(inputs.get(3)));
                    equipment.setState(EquipmentEntity.State.valueOf(inputs.get(4)));
                    equipment.setStorageId(Integer.parseInt(inputs.get(5)));

                    message = SuccessMessages.UPDATE;

                } catch (ParseException e) {
                    message = ErrorMessages.TYPE_ERROR;
                }
                break;
            }
        }
        return message;
    }
}
