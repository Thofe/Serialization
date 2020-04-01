/*
 *  Code written by Blake Mitrick  https://github.com/Thofe
 */
package Serialization;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bmitr
 */
public class Main {
    public static void main(String[] args){
        Person initialPerson = new Person("First", "Last", "1", "2", "3");
        
        
        System.out.println("CSV TEST:");
        //Attempts to serialize a person to a given CSV file
        try{
            //Serializes a person to a given CSV file
            Person.serializeToCSV("data/data.csv", initialPerson);
            
            //Attempts to deserialize a person from a given CSV file
            try{
                // Deserializes a person from the data.csv file    
                Person replicaPerson =  Person.deserializeFromCSV("data/data.csv");
                
                // Prints out the persons info
                printer(initialPerson, replicaPerson); 
            }catch (IOException ex){
                System.out.println("Error in deserialization");
            }
        }catch (IOException ex){
            System.out.println("Error in serialization");
        }
        
        System.out.println();
        System.out.println("CSV TEST CONCLUSION");
       
        
        System.out.println();
        System.out.println();
        
        
        System.out.println("BINARY TEST:");
        //Attempts to serialize a person to a given txt file
        try{
            //Serializes a person to a given txt file
            Person.serializeToBinary("data/data.bin", initialPerson);
            
            //Attempts to deserialize a person from a given txt file
            try{
                // Deserializes a person from the data.txt file
                Person replicaPerson = Person.deserializeFromBinary("data/data.bin");
                
                // Prints out the persons info
                printer(initialPerson, replicaPerson);
            }catch(IOException | ClassNotFoundException ex){
                System.out.println("Error in deserialization");
            }
        }catch (IOException ex){
            System.out.println("Error in serialization");
        }
        
        System.out.println();
        System.out.println("BINARY TEST CONCLUSION");
        
        
        
        System.out.println();
        System.out.println();
        
        
        System.out.println("BINARY TEST WITH SERIALIZER CLASS:");
        //Attempts to serialize a person to a given txt file
        try{
            //Serializes a person to a given txt file
            Serializer.serializeToBinary("data/data.bin", initialPerson);
            
            //Attempts to deserialize a person from a given txt file
            try{
                // Deserializes a person from the data.txt file
                Person replicaPerson = (Person) Serializer.deserializeFromBinary("data/data.bin");
                
                // Prints out the persons info
                printer(initialPerson, replicaPerson);
            }catch(IOException | ClassNotFoundException ex){
                System.out.println("Error in deserialization");
            }
        }catch (IOException ex){
            System.out.println("Error in serialization");
        }
        
        System.out.println();
        
        System.out.println("BINARY TEST WITH SERIALIZER CLASS CONCLUSION");
        
        
        System.out.println();
        System.out.println();
        
        
        System.out.println("XLM TEST:");
        //Attempts to serialize a person to a given txt file
        try{
            //Serializes a person to a given txt file
            Person.serializeToXML("data/data.xml", initialPerson);
            
            //Attempts to deserialize a person from a given txt file
            try{
                // Deserializes a person from the data.txt file
                Person replicaPerson = Person.deserializeFromXML("data/data.xml");
                
                // Prints out the persons info
                printer(initialPerson, replicaPerson);
            }catch(IOException | ClassNotFoundException ex){
                System.out.println("Error in deserialization");
            }
        }catch (IOException | ClassNotFoundException ex){
            System.out.println("Error in serialization");
        }
        
        System.out.println();
        
        System.out.println("XLM TEST CONCLUSION");
    }
    
    public static void printer(Person initialPerson, Person replicaPerson){
        System.out.println("Starting person's state:");
        System.out.println(initialPerson.prettyPrint());

        System.out.println();

        System.out.println("Replica person's state:");
        System.out.println(replicaPerson.prettyPrint());

        System.out.println();

        System.out.println("Are these 2 people's states equal?");
        System.out.println(replicaPerson.equals(initialPerson)); 
    }
}

