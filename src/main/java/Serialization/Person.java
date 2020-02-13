/*
 *  Code written by Blake Mitrick  https://github.com/Thofe
 */
package Serialization;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//Look into how to write and read a test file when specifying the chracter set (NIO instead of IO)
//Hash code comparison override 
//Look at XML Serialization (can be part of person or outside)
//Specify utf8 char set
//make new fork of topsoil and clone that fork

/**
 *
 * @author Bmitr
 */
public class Person implements Comparable<Person>, Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String birthMonth;
    private String birthDay;
    private String birthYear;

    /**
     * Constructs a person with no state
     */
    public Person() {
        this("", "", "", "", "");
    }

    /**
     * Constructs a person with a given state
     *
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @param birthMonth the person's birth month
     * @param birthDay the person's birth day
     * @param birthYear the person's birth year
     */
    public Person(String firstName, String lastName, String birthMonth, String birthDay, String birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
    }

    /**
     * Serializes a person to a given file
     *
     * @param fileName the file to which the data will be saved
     * @param person the person to be serialized
     * @throws IOException
     */
    public static void serializeToCSV(String fileName, Person person) throws IOException {
//        FileWriter fw = new FileWriter(new File(fileName));
//        fw.write("firstName, lastName, birthMonth, birthDay, birthYear" + System.getProperty( "line.separator" ));  
//        fw.write(person.getFirstName() + ", " + person.getLastName() + ", " + person.getBirthMonth() + ", " + person.getBirthDay() + ", " + person.getBirthYear());
//        fw.close();  

        Path file = Paths.get(fileName);

        StringBuilder data = new StringBuilder();
        data.append("firstName, lastName, birthMonth, birthDay, birthYear").append(System.getProperty("line.separator"));
        data.append(person.getFirstName()).append(", ").append(person.getLastName()).append(", ").append(person.getBirthMonth())
                .append(", ").append(person.getBirthDay()).append(", ").append(person.getBirthYear());

        String output = data.toString();
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8.name()));
        out.write(output);
        out.close();
    }

    /**
     * Deserializes a person from a given file
     *
     * @param fileName the file from which the person will be deserialized
     * @return a replica of the person whose state is saved in the given CSV
     * @throws IOException
     */
    public static Person deserializeFromCSV(String fileName) throws IOException {
//        Person person = new Person();
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        br.readLine(); //Reads the first line without recording it 
//        String state = br.readLine();
//         
//        String[] stateArray = state.split(",");
//        
//        person.setFirstName(stateArray[0].trim());
//        person.setLastName(stateArray[1].trim());
//        person.setBirthMonth(stateArray[2].trim());
//        person.setBirthDay(stateArray[3].trim());
//        person.setBirthYear(stateArray[4].trim());
//        return person;

        Path file = Paths.get(fileName);

        List data = Files.readAllLines(file);

        String state = (String) data.get(1);

        String[] stateArray = state.split(",");

        Person replica = new Person();

        replica.setFirstName(stateArray[0].trim());
        replica.setLastName(stateArray[1].trim());
        replica.setBirthMonth(stateArray[2].trim());
        replica.setBirthDay(stateArray[3].trim());
        replica.setBirthYear(stateArray[4].trim());

        return replica;
    }

    
    //Work on serialozer class and remove the binary serializer and deserializer from the person class
    
    /**
     * Serializes a person in binary
     *
     * @param fileName the file to which the person will be serialized
     * @param person the person to be serialized
     * @throws IOException
     */
    public static void serializeToBinary(String fileName, Person person) throws IOException {
//        OutputStream out = new FileOutputStream(fileName);
//        ObjectOutputStream oos = new ObjectOutputStream(out);
//        oos.writeObject(person);
//        oos.close();
//        out.close();
        
        Path file = Paths.get(fileName);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(person);
        out.flush();
        byte[] data = byteStream.toByteArray();

        Files.write(file, data);
    }

    /**
     * Deserializes a person
     *
     * @param fileName the file the person will be deserialized from
     * @return a person with the data from a given file
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Person deserializeFromBinary(String fileName) throws ClassNotFoundException, IOException {
//        InputStream in = new FileInputStream(fileName);
//        ObjectInputStream ois = new ObjectInputStream(in);
//        Person person = (Person) ois.readObject();
//        
//        return person;

        Path file = Paths.get(fileName);
        byte[] data = Files.readAllBytes(file);

        ByteArrayInputStream byteInput = new ByteArrayInputStream(data);
        ObjectInput input = new ObjectInputStream(byteInput);

        Person replica = (Person) input.readObject();

        return replica;
    }

    /**
     * Prints out the state of the Person
     *
     * @return a string formatted to print the persons state in a easily read
     * format
     */
    public StringBuilder prettyPrint() {
        StringBuilder prettyString = new StringBuilder("");
        prettyString.append("First Name: ");
        prettyString.append(getFirstName());
        prettyString.append(System.getProperty("line.separator"));
        prettyString.append("Last Name: ");
        prettyString.append(getLastName());
        prettyString.append(System.getProperty("line.separator"));
        prettyString.append("Birth Month: ");
        prettyString.append(getBirthMonth());
        prettyString.append(System.getProperty("line.separator"));
        prettyString.append("Birth Day: ");
        prettyString.append(getBirthDay());
        prettyString.append(System.getProperty("line.separator"));
        prettyString.append("Birth Year: ");
        prettyString.append(getBirthYear());
        return prettyString;
    }

    @Override
    public boolean equals(Object p) {
        if (p instanceof Person) {
            Person pp = (Person) p;

            boolean comparison = (this.firstName.compareToIgnoreCase(pp.getFirstName()) == 0);
            comparison = comparison && (this.lastName.compareToIgnoreCase(pp.getLastName()) == 0);
            comparison = comparison && (this.birthMonth.compareToIgnoreCase(pp.getBirthMonth()) == 0);
            comparison = comparison && (this.birthDay.compareToIgnoreCase(pp.getBirthDay()) == 0);
            comparison = comparison && (this.birthYear.compareToIgnoreCase(pp.getBirthYear()) == 0);

            return comparison;
        }
        return false;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return this.lastName.compareToIgnoreCase(otherPerson.getLastName());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }
}

