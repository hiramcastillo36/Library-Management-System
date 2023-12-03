package library.library.AdminInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class AdminInterfaceController implements Initializable {
    @FXML
    private Button addBook;

    @FXML
    private Text back;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox container;

    // Supongamos que tienes una lista de libros en tu controlador
    private final List<Book> listaLibros = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = null;
       try {
            rs = DatabaseController.executeQuery("SELECT * FROM Libro");
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String title = rs.getString("Titulo");
                String year = rs.getString("A_publicacion");
                String floor = rs.getString("Piso");
                String shelf = rs.getString("Estante");

                listaLibros.add(new Book(isbn, title, year, floor, shelf));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        // Crear AnchorPane dinámicamente para cada libro y agregarlos al VBox
        for (Book libro : listaLibros) {
            AnchorPane anchorPane = createDataAnchorPane(libro);
            container.getChildren().add(anchorPane);
        }
    }

    private AnchorPane createDataAnchorPane(Book libro) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(38.0);
        anchorPane.setPrefWidth(446.0);
        anchorPane.setStyle("-fx-background-color: lightgray; -fx-border-color: darkgray;");

        Button deleteBook = new Button("Eliminar Libro");
        deleteBook.setOnAction(event -> handleDeleteBook(libro));
        deleteBook.setLayoutX(414.0);
        deleteBook.setLayoutY(6.0);
        // Puedes configurar un evento para el botón de eliminar aquí

        Label labelLibro = new Label(libro.getTitle());
        labelLibro.setLayoutX(14.0);
        labelLibro.setLayoutY(10.0);

        Label labelAutor = new Label("Autor");
        labelAutor.setLayoutX(200.0);
        labelAutor.setLayoutY(10.0);

        Label labelISBN = new Label(String.valueOf(libro.getIsbn()));
        labelISBN.setLayoutX(300.0);
        labelISBN.setLayoutY(10.0);

        anchorPane.getChildren().addAll(deleteBook, labelLibro, labelAutor, labelISBN);
        anchorPane.setUserData(libro);
        return anchorPane;
    }

    private void handleDeleteBook(Book libro) {
        try {
            String deleteBooking = "DELETE FROM PRESTAMO WHERE ISBN = '" + libro.getIsbn() + "'";
            PreparedStatement statement = DatabaseController.getConnection().prepareStatement(deleteBooking);
            DatabaseController.executeInsert(statement);

            String deleteBook = "DELETE FROM LIBRO WHERE ISBN = '" + libro.getIsbn() + "'";
            statement = DatabaseController.getConnection().prepareStatement(deleteBook);
            DatabaseController.executeInsert(statement);
            container.getChildren().removeIf(node -> node.getUserData() == libro);
        } catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void goBack(MouseEvent event) {
        LibraryApplication.changeScene("adminMenu");
    }

    public void askBook(MouseEvent mouseEvent) {
        try {
            // Cargar la nueva escena (en este caso, la escena anterior)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/addBookInterface.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtener el Stage actual y cambiar su escena
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
