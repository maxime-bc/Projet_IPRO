package model.repository;

import constants.Constants;
import data.ApplicationData;
import data.Status;
import model.entity.equipment.EquipmentEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static constants.Constants.ERROR;
import static constants.Constants.SUCCESS;
import static constants.ErrorMessages.*;
import static constants.SuccessMessages.*;

public class EquipmentRepository {

    private static final ApplicationData appData = ApplicationData.getInstance();

    public static Status addEquipment(ArrayList<String> inputs) {
        Status status = new Status();
        if (inputs.size() != 6) {
            status.setStatus(ERROR, ARGS_ERROR);
            return status;
        }

        try {
            DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale.FRANCE);

            appData.getEquipmentEntities().add(new EquipmentEntity(appData.getCurrentEquipmentId(),
                    EquipmentEntity.Owner.valueOf(inputs.get(0)), inputs.get(1), format.parse(inputs.get(2)),
                    Double.parseDouble(inputs.get(3)), EquipmentEntity.State.valueOf(inputs.get(4)), false,
                    Integer.parseInt(inputs.get(5))));

            appData.setCurrentEquipmentId(appData.getCurrentEquipmentId() + 1);

            status.setStatus(SUCCESS, ADD);
        } catch (IllegalArgumentException | ParseException e) {
            status.setStatus(ERROR, ADD_ERROR);
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
                try{
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
