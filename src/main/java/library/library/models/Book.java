package library.library.models;
/**
 * Represents a book in the library.
 */
public class Book {

    String isbn;

    String title;

    String year;

    String floor;

    String shelf;

    /**
     * Constructs a Book object with the specified attributes.
     *
     * @param ISBN  The unique identifier (ISBN) for the book.
     * @param title The title of the book.
     * @param year  The publication year of the book.
     * @param floor The floor where the book is located in the library.
     * @param shelf The shelf where the book is placed on the floor.
     */

    public Book(String ISBN, String title, String year, String floor, String shelf) {
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
    public String getIsbn() {
        return isbn;
    }
    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public String getYear() {
        return year;
    }

    /**
     * Gets the floor where the book is located in the library.
     *
     * @return The floor where the book is located.
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Gets the shelf where the book is placed on the floor.
     *
     * @return The shelf where the book is placed.
     */
    public String getShelf() {
        return shelf;
    }

    /**
     * Sets the ISBN (unique identifier) of the book.
     *
     * @param isbn The ISBN of the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Sets the floor where the book is located in the library.
     *
     * @param floor The floor where the book is located.
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the publication year of the book.
     *
     * @param year The publication year of the book.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Sets the shelf where the book is placed on the floor.
     *
     * @param shelf The shelf where the book is placed.
     */
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }
}