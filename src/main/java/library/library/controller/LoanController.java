package library.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import library.library.models.Book;
import library.library.models.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanController {

    @FXML
    private TableView<Book> loanTable;

    @FXML
    private TableColumn<Book, String> isbnColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> editorialColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> loanColumn;

    @FXML
    private TableColumn<Book, String> categoryColumn;

    @FXML
    private TextField userIdField;

    // Método de inicialización de la clase de controlador
    @FXML
    private void initialize() {
        // Configurar las columnas de la tabla
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        editorialColumn.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Llenar la tabla con los libros prestados del usuario
        populateLoanTable();
    }

    // Método para llenar la tabla con los libros prestados del usuario
    private void populateLoanTable() {
        // Establecer la conexión con la base de datos SQLite
        try (Connection connection = DatabaseController.getConnection()) {
            String query = "SELECT * FROM LIBRO WHERE clave_usuario = clave_usuario";

            // Preparar la consulta
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Configurar el parámetro del usuario
                int userId = obtenerIdUsuarioDesdeTextField();
                preparedStatement.setInt(1, userId);

                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Limpiar la tabla antes de agregar nuevos datos
                    loanTable.getItems().clear();

                    // Llenar la tabla con los resultados de la consulta
                    while (resultSet.next()) {
                        int ISBN = Integer.parseInt(resultSet.getString("ISBN"));
                        String title = resultSet.getString("title");
                        String editorial = resultSet.getString("editorial");
                        String author = resultSet.getString("author");
                        String category = resultSet.getString("category");

                        Book book = new Book(ISBN, title, editorial, author, category);
                        loanTable.getItems().add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener el ID del usuario desde el TextField (ejemplo)
    private int obtenerIdUsuarioDesdeTextField() {
        try {
            // Convertir el texto del TextField a un entero
            return Integer.parseInt(userIdField.getText());
        } catch (NumberFormatException e) {
            // Manejar la excepción si el texto no es un número
            e.printStackTrace();
            return 0;
        }
    }

}