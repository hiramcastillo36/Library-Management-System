package library.library.models;
/**
 * Represents a book in the library.
 */
public class Book {

    private String name;
    private int ISBN;
    private String title;
    private String editorial;
    private String author;
    private boolean loan;
    private Category category;
    /**
     * Constructs a Book object with the specified attributes.
     *
     * @param name      The name of the book.
     * @param ISBN      The unique identifier (ISBN) for the book.
     * @param title     The title of the book.
     * @param editorial The editorial that published the book.
     * @param author    The author(s) of the book.
     * @param loan      The loan status of the book.
     * @param category  The category to which the book belongs.
     */
    public Book(String name, int ISBN, String title, String editorial, String author, boolean loan, Category category)
    {
        this.name = name;
        this.ISBN = ISBN;
        this.title = title;
        this.editorial = editorial;
        this.author = author;
        this.loan = loan;
        this.category = category;
    }
    /**
     * Gets the name of the book.
     *
     * @return The name of the book.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the ISBN (unique identifier) of the book.
     *
     * @return The ISBN of the book.
     */
    public int getISBN() {
        return ISBN;
    }
    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the editorial that published the book.
     *
     * @return The editorial of the book.
     */
    public String getEditorial() {
        return editorial;
    }
    /**
     * Gets the author(s) of the book.
     *
     * @return The author(s) of the book.
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Checks the loan status of the book.
     *
     * @return True if the book is on loan, false otherwise.
     */
    public boolean isLoan() {
        return loan;
    }
    /**
     * Gets the category to which the book belongs.
     *
     * @return The category of the book.
     */
    public Category getCategory() {
        return category;
    }

}
