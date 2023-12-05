package library.library.AdminInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Author;
import library.library.models.Book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller class for adding a new book in the administrator interface.
 * Manages the input validation, database insertion, and navigation.
 */
public class addBookController {

    @FXML
    private Text back;

    @FXML
    private Button Enviar;

    @FXML
    private TextField Shelf;

    @FXML
    private TextField ISBN;

    @FXML
    private TextField Floor;

    @FXML
    private TextField Title;

    @FXML
    private TextField Year;

    @FXML
    private TextField Name;

    @FXML
    private TextField Lastname;

    /**
     * Handles the action to navigate back to the books dashboard.
     *
     * @param event The mouse event triggering the navigation.
     */
    @FXML
    void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/booksDashboard.fxml"));
            Scene previousScene = new Scene(loader.load());

            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action to add a new book.
     *
     * @param mouseEvent The mouse event triggering the action.
     */
    public void Send(MouseEvent mouseEvent) {

        // Input fields
        String isbn = ISBN.getText();
        String title = Title.getText();
        String year = Year.getText();
        String floor = Floor.getText();
        String shelf = Shelf.getText();
        String name = Name.getText();
        String lastname = Lastname.getText();

        // Input validation
        if (isbn.isEmpty() || isbn.length() != 6 || !isbn.matches("\\d+") || DatabaseController.isISBNExists(isbn)) {
            showErrorAlert("Error de ISBN", "Verifique el formato y la existencia del ISBN.");
            return;
        }

        if (title.isEmpty() || DatabaseController.isTitleExists(title)) {
            showErrorAlert("Error de Título", "El título no puede estar vacío y debe ser único.");
            return;
        }

        if (year.isEmpty() || !year.matches("\\d+")) {
            showErrorAlert("Error de Año", "El año no puede estar vacío y debe contener solo números.");
            return;
        }

        if (name.isEmpty() || !name.matches("[a-zA-Z]+") || lastname.isEmpty() || !lastname.matches("[a-zA-Z]+")) {
            showErrorAlert("Error de Nombre o Apellido", "Nombre y apellido no pueden estar vacíos y deben contener solo letras.");
            return;
        }

        if (floor.isEmpty() || !floor.matches("\\d+") || shelf.isEmpty() || !shelf.matches("\\d+")) {
            showErrorAlert("Error de Piso o Estante", "Piso y estante no pueden estar vacíos y deben contener solo números.");
            return;
        }

        // Convert ISBN to Integer
        Integer ISBN = Integer.valueOf(isbn);

        // Create Book and Author objects
        Book nuevoLibro = new Book(isbn, title, year, floor, shelf);
        Author author = new Author(ISBN, name, lastname);

        // Insert into the database
        insertBook(nuevoLibro);
        insertAuthor(author);

        // Navigate back to the books dashboard
        try {
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/booksDashboard.fxml"));
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
     * Inserts a new book into the database.
     *
     * @param book The Book object to be inserted.
     */
    public void insertBook(Book book) {
        ResultSet rs = null;
        String query = "INSERT INTO Libro (ISBN, Titulo, A_publicacion, Id_Editorial, Piso, Estante) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseController.getConnection();  // Implement your logic to get a connection
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getYear());
            preparedStatement.setInt(4, 123456);  // Replace with the actual Id_Editorial
            preparedStatement.setString(5, book.getFloor());
            preparedStatement.setString(6, book.getShelf());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Exception handling, adjust as necessary
        }
    }

    /**
     * Inserts a new author into the database.
     *
     * @param author The Author object to be inserted.
     */
    public void insertAuthor(Author author) {
        String isbn = ISBN.getText();
        Integer ISBN = Integer.valueOf(isbn);
        ResultSet rs = null;
        String query = "INSERT INTO Autores (ISBN, NombreAutor, ApellidoPaterno) VALUES (?, ?, ?)";

        try {
            Connection connection = DatabaseController.getConnection();  // Implement your logic to get a connection
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, ISBN);
            preparedStatement.setString(2, author.getName());
            preparedStatement.setString(3, author.getLastName());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Exception handling, adjust as necessary
        }
    }
}