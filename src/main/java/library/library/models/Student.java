package library.library.models;
/**
 * Represents a student in the library.
 */
public class Student {
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String id;
    /**
     * Constructs a Student object with the specified attributes.
     *
     * @param firstName      The first name of the student.
     * @param lastName       The last name of the student.
     * @param secondLastName The second last name of the student.
     * @param id       The unique identifier for the student.
     */
    public Student(String firstName, String lastName, String secondLastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
}
