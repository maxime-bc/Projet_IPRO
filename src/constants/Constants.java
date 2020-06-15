package constants;

import java.util.List;

public class Constants {
    public static final String APP_DATA_FILE = "app.data";
    public static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy";

    public static final boolean SUCCESS = true;
    public static final boolean ERROR = false;

    public static final Integer ACTION = 0;
    public static final Integer OBJECT = 1;

    public static final String DISPLAY_ACTION = "display";
    public static final String ADD_ACTION = "add";
    public static final String UPDATE_ACTION = "update";
    public static final String DELETE_ACTION = "delete";
    public static final String RETURN_ACTION = "return";
    public static final String QUIT_ACTION = "quit";

    public static final String USER_OBJECT = "user";
    public static final String BORROWING_OBJECT = "borrowing";
    public static final String EQUIPMENT_OBJECT = "equipment";
    public static final String STORAGE_OBJECT = "storage";

    public static final List<String> ACTIONS = List.of(DISPLAY_ACTION, ADD_ACTION, UPDATE_ACTION, DELETE_ACTION, RETURN_ACTION, QUIT_ACTION);
    public static final List<String> OBJECTS = List.of(USER_OBJECT, BORROWING_OBJECT, EQUIPMENT_OBJECT, STORAGE_OBJECT);

    public static final Integer GAME_CONTROLLER = 1;
    public static final Integer HEADSET = 2;
    public static final Integer MOUSE = 3;
    public static final Integer PHONE = 4;
    public static final Integer TABLET = 5;
    public static final Integer VR_CONTROLLER = 6;
    public static final Integer VR_HEADSET = 7;
    public static final Integer WEBCAM = 8;

    public static final Integer TOTAL = 1;
    public static final Integer AVAILABLE = 2;
    public static final Integer BORROWED = 3;
    public static final Integer BY_TYPE = 4;
    public static final Integer BY_REASON = 2;
    public static final Integer BY_BORROWER = 3;
    public static final Integer OVERDUE = 4;
}
