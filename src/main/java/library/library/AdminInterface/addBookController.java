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

    @FXML
    void goBack(MouseEvent event) {
        try {
            // Cargar la nueva escena (en este caso, la escena anterior)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/booksDashboard.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtener el Stage actual y cambiar su escena
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Send(MouseEvent mouseEvent) {

        String isbn = ISBN.getText();
        String title = Title.getText();
        String year = Year.getText();
        String floor = Floor.getText();
        String shelf = Shelf.getText();
        String name = Name.getText();
        String lastname = Lastname.getText();

        //valida que el isbn no este vacio
        if (isbn.isEmpty()) {
            showErrorAlert("Error de ISBN", "El ISBN no puede estar vacío.");
            return;
        }

        // Validar que el ISBN tenga exactamente 6 dígitos
        if (isbn.length() != 3) {
            showErrorAlert("Error de ISBN", "El ISBN debe tener exactamente 3 dígitos.");
            return;
        }

        if (!isbn.matches("\\d+")) {
            showErrorAlert("Error de ISBN", "El ISBN debe contener solo números.");
            return;
        }

        // Validar que el ISBN no exista previamente en la base de datos
        if (DatabaseController.isISBNExists(isbn)) {
            showErrorAlert("Error de ISBN", "El ISBN ya existe en la base de datos.");
            return;
        }

        // Validar que el título no exista previamente en la base de datos
        if (DatabaseController.isTitleExists(title)) {
            showErrorAlert("Error de Título", "El título ya existe en la base de datos.");
            return;
        }

        // Validar que el título no esté vacío
        if (title.isEmpty()) {
            showErrorAlert("Error de Título", "El título no puede estar vacío.");
            return;
        }

        // Validar que el año no esté vacío y contenga solo números
        if (year.isEmpty() || !year.matches("\\d+")) {
            showErrorAlert("Error de Año", "El año no puede estar vacío y debe contener solo números.");
            return;
        }

        /// Validar que el nombre no esté vacío y contenga solo letras
        if (name.isEmpty() || !name.matches("[a-zA-Z]+")) {
            showErrorAlert("Error de Nombre", "El nombre no puede estar vacío y debe contener solo letras.");
            return;
        }

        // Validar que el apellido no esté vacío y contenga solo letras
        if (lastname.isEmpty() || !lastname.matches("[a-zA-Z]+")) {
            showErrorAlert("Error de Apellido", "El apellido no puede estar vacío y debe contener solo letras.");
            return;
        }

        // Validar que el piso no esté vacío y contenga solo números
        if (floor.isEmpty() || !floor.matches("\\d+")) {
            showErrorAlert("Error de Piso", "El piso no puede estar vacío y debe contener solo números.");
            return;
        }

        // Validar que el estante no esté vacío y contenga solo números
        if (shelf.isEmpty() || !shelf.matches("\\d+")) {
            showErrorAlert("Error de Estante", "El estante no puede estar vacío y debe contener solo números.");
            return;
        }

        Integer ISBN = Integer.valueOf(isbn);
        // Si todas las validaciones pasan, puedes crear un nuevo libro y agregarlo a la base de datos
        Book nuevoLibro = new Book(ISBN, title, year, floor, shelf);
        Author author = new Author(ISBN, name, lastname);

        insertBook(nuevoLibro);
        try {
            // Cargar la nueva escena (en este caso, la escena anterior)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/booksDashboard.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtener el Stage actual y cambiar su escena
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void insertBook(Book book) {
        ResultSet rs = null;
        String query = "INSERT INTO Libro (ISBN, Titulo, A_publicacion, Id_Editorial, Piso, Estante) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Crea la conexión y prepara la declaración
            Connection connection = DatabaseController.getConnection();  // Implementa tu lógica para obtener una conexión
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Establece los parámetros de la declaración con los valores del libro
            preparedStatement.setInt(1, book.getISBN());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getYear());
            preparedStatement.setInt(4, 123456);
            preparedStatement.setString(5, book.getFloor());
            preparedStatement.setString(6, book.getShelf());


            // Ejecuta la actualización
            preparedStatement.executeUpdate();

            // Cierra la conexión y la declaración
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones, ajusta según sea necesario
        }
    }

    public void insertAutohr(Author author) {
        String isbn = ISBN.getText();
        Integer ISBN = Integer.valueOf(isbn);
        ResultSet rs = null;
        String query = "INSERT INTO Autor (ISBN, Nombre, Apellido) VALUES (?, ?, ?)";

        try {
            // Crea la conexión y prepara la declaración
            Connection connection = DatabaseController.getConnection();  // Implementa tu lógica para obtener una conexión
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Establece los parámetros de la declaración con los valores del libro
            preparedStatement.setInt(1, ISBN);
            preparedStatement.setString(2, author.getName());
            preparedStatement.setString(3, author.getLastName());

            // Ejecuta la actualización
            preparedStatement.executeUpdate();

            // Cierra la conexión y la declaración
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones, ajusta según sea necesario
        }
    }
}
