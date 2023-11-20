package library.library.models;

import javafx.scene.control.skin.LabeledSkinBase;

public class Author {
    private int ISBN;
    private String name;
    private String lastName;

    public Author(int ISBN, String name, String lastName) {
        this.ISBN = ISBN;
        this.name = name;
        this.lastName = lastName;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}