package Controller;

import java.io.*;

public class Serialize {

    public static Object deserialize() {
        Object object = null;
        try {
            ObjectInputStream out = new ObjectInputStream(new FileInputStream("MyData.ser"));
            object = out.readObject();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return object;
    }

    public static void serialize(Object object, String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(object);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
