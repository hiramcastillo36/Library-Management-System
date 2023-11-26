package library.library.models;
/**
 * Represents a book in the library.
 */
public class Book {
    ;
    private int ISBN;
    private String title;
    private String editorial;
    private String author;
    private String category;
    /**
     * Constructs a Book object with the specified attributes.
     *
     * @param ISBN      The unique identifier (ISBN) for the book.
     * @param title     The title of the book.
     * @param editorial The editorial that published the book.
     * @param author    The author(s) of the book.
     * @param category  The category to which the book belongs.
     */
    public Book(int ISBN, String title, String editorial, String author, String category)
    {
        this.ISBN = ISBN;
        this.title = title;
        this.editorial = editorial;
        this.author = author;
        this.category = category;
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
     * Gets the category to which the book belongs.
     *
     * @return The category of the book.
     */
    public String getCategory() {
        return category;
    }

}
