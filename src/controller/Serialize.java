package controller;

import java.io.*;


/**
 * Serialize and deserialize an object to a file.
 * Used to ensure data persistence of the application, by saving ApplicationData into a file.
 */
public class Serialize {

    /**
     * Deserialize an object from a file.
     *
     * @param fileName the file in which the object is serialized.
     * @return the object contained in the file,
     * and null if the file doesn't exists or its data cannot be used to create an object.
     */
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

    /**
     * Serialise an object into a file.
     *
     * @param object   object to serialise into the file.
     * @param fileName file in which the serialised object is going to be stored.
     */
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
