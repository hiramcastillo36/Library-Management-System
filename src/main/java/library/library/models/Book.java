package library.library.models;

public class Book {

    private String name;
    private int ISBN;
    private String title;
    private String editorial;
    private String author;
    private boolean loan;
    private Category category;

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

    public String getName() {
        return name;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isLoan() {
        return loan;
    }

    public Category getCategory() {
        return category;
    }

}
