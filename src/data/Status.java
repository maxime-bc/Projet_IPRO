package data;

/**
 * Used represent the status of a function.
 * The class store a code and a message.
 */
public class Status {

    private boolean code;
    private String message;

    /**
     * Construct a status.
     *
     * @param code    true if the function succeeded, or false if it failed.
     * @param message success or error message of the function.
     * @see constants.ErrorMessages
     * @see constants.SuccessMessages
     */
    public Status(boolean code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Empty status constructor.
     */
    public Status() {
        this.code = false;
        this.message = "";
    }

    /**
     * Set status.
     *
     * @param code    true if the function succeeded, or false if it failed.
     * @param message success or error message of the function.
     * @see constants.ErrorMessages
     * @see constants.SuccessMessages
     */
    public void setStatus(boolean code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
