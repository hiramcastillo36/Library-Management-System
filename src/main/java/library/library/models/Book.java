package library.library.models;
/**
 * Represents a book in the library.
 */
public class Book {

     Integer isbn;
    String title;

     String year;

     String floor;

     String shelf;


    /**
     * Constructs a Book object with the specified attributes.
     *
     * @param ISBN      The unique identifier (ISBN) for the book.
     * @param title     The title of the book.
     * @param editorial The editorial that published the book.
     * @param author    The author(s) of the book.
     * @param loan      The loan status of the book.
     * @param category  The category to which the book belongs.
     */
    public Book(int ISBN, String title, String year, String floor, String shelf) {
        this.isbn = ISBN;
        this.title = title;
        this.year = year;
        this.floor = floor;
        this.shelf = shelf;
    }

    /**
     * Gets the ISBN (unique identifier) of the book.
     *
     * @return The ISBN of the book.
     */
    public int getISBN() {
        return isbn;
    }
    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public String getFloor() {
        return floor;
    }

    public String getShelf() {
        return shelf;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }
}
