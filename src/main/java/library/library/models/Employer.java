package library.library.models;
import java.util.Date;

public class Employer {
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String NSS;
    private Date initDate;

    public Employer(String firstName, String lastName, String secondLastName, String NSS, Date initDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.NSS = NSS;
        this.initDate = initDate;
    }

}
