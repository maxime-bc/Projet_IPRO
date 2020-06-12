package data;

public class Status {

    private boolean code;
    private String message;

    public Status(boolean code, String message){
        this.code = code;
        this.message = message;
    }

    public Status(){
        this.code = false;
        this.message = "";
    }

    public void setStatus(boolean code, String message){
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
