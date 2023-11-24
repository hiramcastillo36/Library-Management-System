package library.library.models;
/**
 * Represents a book publisher.
 */
public class Editorial {
    private String name;
    private String id;
    /**
     * Constructs an Editorial object with the specified name and identifier.
     *
     * @param name The name of the editorial.
     * @param id   The unique identifier for the editorial.
     */
    public Editorial(String name, String id)
    {
        this.name = name;
        this.id = id;
    }
}
