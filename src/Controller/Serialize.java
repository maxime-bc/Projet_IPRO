package Controller;

import java.io.*;

public class Serialize {

    public static Object deserialize(String fileName) {
        Object object = null;
        try {
            ObjectInputStream out = new ObjectInputStream(new FileInputStream(fileName));
            object = out.readObject();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return object;
    }

    public static void serialize(Object object, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(object);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
