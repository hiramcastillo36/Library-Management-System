package library.library.AdminInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the administrator interface.
 * Manages the display of books and authors, deletion of books, and navigation.
 */
public class AdminInterfaceController implements Initializable {
    @FXML
    private Button addBook;

    @FXML
    private Text back;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox container;

    private final List<Book> listaLibros = new ArrayList<>();
    private final List<String> listaAutores = new ArrayList<>();

    /**
     * Initializes the controller with book and author data.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources for the root object, or null if none.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Autores\n" +
                    "INNER JOIN main.LIBRO L on L.ISBN = Autores.ISBN;");

            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String title = rs.getString("Titulo");
                String year = rs.getString("A_publicacion");
                String floor = rs.getString("Piso");
                String shelf = rs.getString("Estante");
                String author = rs.getString("NombreAutor");

                listaLibros.add(new Book(isbn, title, year, floor, shelf));
                listaAutores.add(author);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Book libro : listaLibros) {
            AnchorPane anchorPane = createDataAnchorPane(libro, listaAutores.get(listaLibros.indexOf(libro)));
            container.getChildren().add(anchorPane);
        }
    }

    /**
     * Creates an AnchorPane for displaying book and author information.
     *
     * @param libro  The Book object to be displayed.
     * @param author The author of the book.
     * @return The created AnchorPane.
     */
    private AnchorPane createDataAnchorPane(Book libro, String author) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(38.0);
        anchorPane.setPrefWidth(446.0);
        anchorPane.setStyle("-fx-background-color: lightgray; -fx-border-color: darkgray;");

        Button deleteBook = new Button("Eliminar Libro");
        deleteBook.setOnAction(event -> handleDeleteBook(libro));
        deleteBook.setLayoutX(414.0);
        deleteBook.setLayoutY(6.0);

        Label labelLibro = new Label(libro.getTitle());
        labelLibro.setLayoutX(14.0);
        labelLibro.setLayoutY(10.0);

        Label labelAutor = new Label(author);
        labelAutor.setLayoutX(200.0);
        labelAutor.setLayoutY(10.0);

        Label labelISBN = new Label(String.valueOf(libro.getISBN()));
        labelISBN.setLayoutX(300.0);
        labelISBN.setLayoutY(10.0);

        anchorPane.getChildren().addAll(deleteBook, labelLibro, labelAutor, labelISBN);
        anchorPane.setUserData(libro);
        return anchorPane;
    }

    /**
     * Handles the deletion of a book.
     *
     * @param libro The Book object to be deleted.
     */
    private void handleDeleteBook(Book libro) {
        if (isBookBorrowed(libro)) {
            showErrorAlert("Error", "El libro no se puede eliminar porque está prestado");
            return;
        } else {
            deleteBook(libro);
        }
    }

    /**
     * Navigates back to the administrator menu.
     *
     * @param event The mouse event triggering the navigation.
     */
    @FXML
    void goBack(MouseEvent event) {
        LibraryApplication.changeScene("adminMenu");
    }

    /**
     * Navigates to the add book interface.
     *
     * @param mouseEvent The mouse event triggering the navigation.
     */
    public void askBook(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/addBookInterface.fxml"));
            Scene previousScene = new Scene(loader.load());

            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an error alert with the specified title and content.
     *
     * @param title   The title of the error alert.
     * @param content The content of the error alert.
     */
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Checks if a book is currently borrowed.
     *
     * @param libro The Book object to check.
     * @return True if the book is borrowed, false otherwise.
     */
    private boolean isBookBorrowed(Book libro) {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM PRESTAMO WHERE ISBN = '" + libro.getISBN() + "'");
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a book from the database and updates the UI.
     *
     * @param libro The Book object to be deleted.
     */
    public void deleteBook(Book libro) {
        try {
            String deleteBook = "DELETE FROM LIBRO WHERE ISBN = '" + libro.getISBN() + "'";
            DatabaseController.getConnection().prepareStatement(deleteBook);
            PreparedStatement statement;
            statement = DatabaseController.getConnection().prepareStatement(deleteBook);
            DatabaseController.executeInsert(statement);
            container.getChildren().removeIf(node -> node.getUserData() == libro);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}