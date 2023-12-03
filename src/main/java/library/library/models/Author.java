package library.library.models;
/**
 * Represents an author of books.
 */
public class Author {
    private final int ISBN;
    private final String name;
    private final String lastName;
    /**
     * Constructs an Author object with the specified ISBN, name, and last name.
     *
     * @param ISBN     The unique identifier for the author´s book.
     * @param name     The first name of the author.
     * @param lastName The last name of the author.
     */
    public Author(int ISBN, String name, String lastName) {
        this.ISBN = ISBN;
        this.name = name;
        this.lastName = lastName;
    }
    /**
     * Gets the ISBN (unique identifier) of the author.
     *
     * @return The ISBN of the author.
     */
    public int getISBN() {
        return ISBN;
    }
    /**
     * Gets the first name of the author.
     *
     * @return The first name of the author.
     */
    public String getName() {
        return name;
    }

}