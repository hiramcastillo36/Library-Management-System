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

     String editorial;


    /**
     * Constructs a Book object with the specified attributes.
     *
     * @param ISBN      The unique identifier (ISBN) for the book.
     * @param title     The title of the book.
     * @param year      The publication year of the book.
     * @param floor     The floor where the book is located in the library.
     * @param shelf     The shelf where the book is placed on the floor.
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
