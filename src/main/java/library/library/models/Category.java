package library.library.models;
/**
 * Represents a category of books.
 */
public class Category {

    private int id;
    private String name;
    /**
     * Constructs a Category object with the specified identifier and name.
     *
     * @param id   The unique identifier for the category.
     * @param name The name of the category.
     */
    public Category(int id,String name){
        this.id = id;
        this.name = name;
    }
}
