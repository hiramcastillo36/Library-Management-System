package library.library.models;
/** Represents the account information in the library system.
*/
public class Account {
    private final String email;
    private String password;
    private final String accountType;

    /**
     * Constructs an Account object with the specified email, password, and account type.
     *
     * @param email       The email associated with the account.
     * @param password    The password for the account.
     * @param accountType The type of the account.
     */
    public Account(String email, String password, String accountType) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }
    /**
     * Constructs an Account object with the specified email and account type.
     *
     * @param email       The email associated with the account.
     * @param accountType The type of the account (e.g., user role).
     */
    public Account(String email, String accountType) {
        this.email = email;
        this.accountType = accountType;
    }

    /**
     * Gets the email associated with the account.
     *
     * @return The email of the account.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gets the account type associated with the account.
     *
     * @return The account type.
     */
    public String getAccountType() {
        return accountType;
    }
}
