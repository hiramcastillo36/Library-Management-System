package library.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.models.Account;
import library.library.models.Book;
import library.library.models.Student;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoanController implements Initializable {

    @FXML
    private Text back;

    @FXML
    private VBox container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ResultSet rs = null;
        try {
            String query = LibraryApplication.getSession().getEmail();
            ResultSet st;
            st = DatabaseController.executeQuery("SELECT * FROM estudiantes WHERE correo_electronico = '" + query + "'");
            if(st.next()){
                System.out.println(st.getString("Clave_Usuario"));
                rs = DatabaseController.executeQuery("SELECT * FROM Autores\n" +
                        "INNER JOIN main.LIBRO L on L.ISBN = Autores.ISBN\n" +
                        "INNER JOIN main.PRESTAMO P on L.ISBN = P.ISBN\n" +
                        "WHERE P.Clave_Usuario = " + st.getString("Clave_Usuario")
                );

                while (rs.next()) {
                    System.out.println(rs.getString("Titulo"));
                    System.out.println(rs.getString("NombreAutor"));
                    AnchorPane anchorPane = createDataAnchorPane(rs.getString("Titulo"), rs.getString("NombreAutor"), rs.getString("ISBN"));
                    container.getChildren().add(anchorPane);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        // Crear AnchorPane dinámicamente para cada libro y agregarlos al VBox
    }

    private AnchorPane createDataAnchorPane(String title, String author, String isbn) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(38.0);
        anchorPane.setPrefWidth(446.0);
        anchorPane.setStyle("-fx-background-color: lightgray; -fx-border-color: darkgray;");

        // Puedes configurar un evento para el botón de eliminar aquí

        Label labelLibro = new Label(title);
        labelLibro.setLayoutX(14.0);
        labelLibro.setLayoutY(10.0);

        Label labelAutor = new Label(author);
        labelAutor.setLayoutX(200.0);
        labelAutor.setLayoutY(10.0);

        Label labelISBN = new Label(isbn);
        labelISBN.setLayoutX(300.0);
        labelISBN.setLayoutY(10.0);

        anchorPane.getChildren().addAll(labelLibro, labelAutor, labelISBN);

        return anchorPane;
    }

    @FXML
    void goBack(MouseEvent event) {
        try {
            // Cargar la nueva escena (en este caso, la escena anterior)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/Interface.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtener el Stage actual y cambiar su escena
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}