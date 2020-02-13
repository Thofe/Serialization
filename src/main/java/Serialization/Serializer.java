/*
 *  Code written by Blake Mitrick  https://github.com/Thofe
 */
package Serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Path file = Paths.get(fileName);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(o);
        out.flush();
        byte[] data = byteStream.toByteArray();

        Files.write(file, data);
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
        Path file = Paths.get(fileName);
        byte[] data = Files.readAllBytes(file);

        ByteArrayInputStream byteInput = new ByteArrayInputStream(data);
        ObjectInput input = new ObjectInputStream(byteInput);

        Object replica = input.readObject();

        return replica;
    }
}

