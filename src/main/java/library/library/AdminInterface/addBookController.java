package library.library.AdminInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/AdminInterface.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtener el Stage actual y cambiar su escena
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de excepciones, ajusta según sea necesario
        }
    }

    public void Send(MouseEvent mouseEvent) {

        String isbn = ISBN.getText();
        String title = Title.getText();

        // Validar que el ISBN tenga exactamente 6 dígitos
        if (isbn.length() != 6) {
            // Mostrar mensaje de error o tomar la acción apropiada
            System.out.println("El ISBN debe tener exactamente 6 dígitos.");
            return;
        }

        // Validar que el ISBN no exista previamente en la base de datos
        if (DatabaseController.isISBNExists(isbn)) {
            // Mostrar mensaje de error o tomar la acción apropiada
            System.out.println("El ISBN ya existe en la base de datos.");
            return;
        }

        // Validar que el título no exista previamente en la base de datos
        if (DatabaseController.isTitleExists(title)) {
            // Mostrar mensaje de error o tomar la acción apropiada
            System.out.println("El título ya existe en la base de datos.");
            return;
        }

        String year = Year.getText();
        String floor = Floor.getText();
        String shelf = Shelf.getText();
        String name = Name.getText();
        String lastname = Lastname.getText();

        Integer ISBN = Integer.valueOf(isbn);
        // Si todas las validaciones pasan, puedes crear un nuevo libro y agregarlo a la base de datos
        Book nuevoLibro = new Book(ISBN, title, year, floor, shelf);
        Author author = new Author(ISBN, "nombre", "Apellido");

        insertBook(nuevoLibro);
        try {
            // Cargar la nueva escena (en este caso, la escena anterior)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/AdminInterface.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtener el Stage actual y cambiar su escena
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de excepciones, ajusta según sea necesario
        }
    }


    public static void insertBook(Book book) {
        ResultSet rs = null;
        String query = "INSERT INTO Libro (ISBN, Titulo, A_publicacion, Piso, Estante) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            // Crea la conexión y prepara la declaración
            Connection connection = DatabaseController.getConnection();  // Implementa tu lógica para obtener una conexión
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Establece los parámetros de la declaración con los valores del libro
            preparedStatement.setInt(1, book.getISBN());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getYear());
            preparedStatement.setString(4, book.getFloor());
            preparedStatement.setString(5, book.getShelf());

            // Ejecuta la actualización
            preparedStatement.executeUpdate();

            // Cierra la conexión y la declaración
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones, ajusta según sea necesario
        }
    }

    public static void insertBook(Author author) {
        ResultSet rs = null;
        String query = "INSERT INTO Autor (ISBN, Titulo, A_publicacion, Piso, Estante) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            // Crea la conexión y prepara la declaración
            Connection connection = DatabaseController.getConnection();  // Implementa tu lógica para obtener una conexión
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Establece los parámetros de la declaración con los valores del libro
            preparedStatement.setInt(1, ISBN.getText());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getYear());
            preparedStatement.setString(4, book.getFloor());
            preparedStatement.setString(5, book.getShelf());

            // Ejecuta la actualización
            preparedStatement.executeUpdate();

            // Cierra la conexión y la declaración
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepciones, ajusta según sea necesario
        }
    }
}
