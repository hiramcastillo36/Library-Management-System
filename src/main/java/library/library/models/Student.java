package library.library.models;

/**
 * Represents a student in the library.
 */
public class Student {

    /**
     * The first name of the student.
     */
    private final String firstName;

    /**
     * The last name of the student.
     */
    private final String lastName;

    /**
     * The second last name of the student.
     */
    private final String secondLastName;

    /**
     * The unique identifier for the student.
     */
    private final String id;

    /**
     * Constructs a Student object with the specified attributes.
     *
     * @param firstName      The first name of the student.
     * @param lastName       The last name of the student.
     * @param secondLastName The second last name of the student.
     * @param id             The unique identifier for the student.
     */
    public Student(String firstName, String lastName, String secondLastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.id = id;
    }

    /**
     * Gets the unique identifier of the student.
     *
     * @return The unique identifier of the student.
     */
    public String getId() {
        return id;
    }
}