/*
 *  Code written by Blake Mitrick  https://github.com/Thofe
 */
package Serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 *
 * @author Bmitr
 */
public class Serializer implements Serializable {
    
    //Same as the one in person
    /**
     * Serializes an object to a file
     * 
     * @param fileName the file in which the object is being serialized 
     * @param o the object being serialized 
     * @throws IOException 
     */
    public static void serializeToBinary(String fileName, Object o)throws IOException{
        OutputStream out = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(o);
        oos.close();
        out.close();
    }
    
    /**
     * Returns an object based on data stored in a file 
     * 
     * @param fileName the file that the object will be deserialized from
     * @return an object constructed by the data in the file
     * @throws ClassNotFoundException
     * @throws IOException 
     */
    public static Object deserializeFromBinary(String fileName) throws ClassNotFoundException, IOException {
        InputStream in = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(in);
        Object o = (Person) ois.readObject();
        
        return o;
    }
}

