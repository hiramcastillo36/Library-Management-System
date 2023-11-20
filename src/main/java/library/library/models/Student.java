package library.library.models;

public class Student {
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String uniqueID;

    public Student(String firstName, String lastName, String secondLastName, String uniqueID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.uniqueID = uniqueID;
    }

}
