package library.library.models;
import java.util.Date;
/**
 * Represents an employee in the library.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String NSS;
    private Date initDate;
    /**
     * Constructs an Employer object with the specified attributes.
     *
     * @param firstName      The first name of the employee.
     * @param lastName       The last name of the employee.
     * @param secondLastName The second last name of the employee.
     * @param NSS            The National Social Security (NSS) number of the employee.
     * @param initDate       The date when the employee started working.
     */
    public Employee(String firstName, String lastName, String secondLastName, String NSS, Date initDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.NSS = NSS;
        this.initDate = initDate;
    }

}
