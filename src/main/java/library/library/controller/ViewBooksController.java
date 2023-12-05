package library.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import library.library.LibraryApplication;

import java.sql.ResultSet;

/**
 * Controller class for viewing books.
 * Manages the UI elements and user interactions related to viewing books.
 */
public class ViewBooksController {

    @FXML
    private VBox container;

    @FXML
    private TextField searchField;

    /**
     * Initializes the ViewBooksController, populating the list of books for viewing.
     */
    @FXML
    private void initialize() {
        ResultSet rs = null;
        try {
            // Retrieve book information from the database, including authors and borrowers
            rs = DatabaseController.executeQuery("SELECT * FROM Autores\n" +
                    "INNER JOIN main.LIBRO L on L.ISBN = Autores.ISBN\n" +
                    "INNER JOIN main.PRESTAMO P on L.ISBN = P.ISBN");

            // Create and add UI elements for each book to the view
            while (rs.next()) {
                AnchorPane anchorPane = createDataAnchorPane(rs.getString("Titulo"),
                        rs.getString("NombreAutor"), rs.getString("ISBN"),
                        rs.getString("Clave_Usuario"));
                container.getChildren().add(anchorPane);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates an AnchorPane with information for a book.
     *
     * @param title        The title of the book.
     * @param author       The author of the book.
     * @param isbn         The ISBN of the book.
     * @param clave_usuario The user ID associated with the book (for borrowed books).
     * @return The created AnchorPane.
     */
    private AnchorPane createDataAnchorPane(String title, String author, String isbn, String clave_usuario) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(38.0);
        anchorPane.setPrefWidth(446.0);
        anchorPane.setStyle("-fx-background-color: lightgray; -fx-border-color: darkgray;");

        Label claveUsuario = new Label(clave_usuario);
        claveUsuario.setLayoutX(414.0);
        claveUsuario.setLayoutY(6.0);

        Label labelLibro = new Label(title);
        labelLibro.setLayoutX(14.0);
        labelLibro.setLayoutY(10.0);

        Label labelAutor = new Label(author);
        labelAutor.setLayoutX(200.0);
        labelAutor.setLayoutY(10.0);

        Label labelISBN = new Label(isbn);
        labelISBN.setLayoutX(300.0);
        labelISBN.setLayoutY(10.0);

        anchorPane.getChildren().addAll(labelLibro, labelAutor, labelISBN, claveUsuario);

        return anchorPane;
    }

    /**
     * Handles the event when the user clicks the "Back" button.
     * Returns to the admin menu view.
     *
     * @param event The mouse event triggered by the user.
     */
    @FXML
    void goBack(MouseEvent event) {
        LibraryApplication.changeScene("adminMenu");
    }
}