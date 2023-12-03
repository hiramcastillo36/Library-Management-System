package library.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.models.Book;
import library.library.models.Student;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

public class UserLoanController {
    @FXML
    private VBox container;

    @FXML
    private Text back;
    @FXML
    private TextField searchField;

    @FXML
    private void initialize() {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Autores\n" +
                    "INNER JOIN main.LIBRO L on L.ISBN = Autores.ISBN");

            while (rs.next()) {
                AnchorPane anchorPane = createDataAnchorPane(rs.getString("Titulo"),
                        rs.getString("NombreAutor"), rs.getString("ISBN"));
                container.getChildren().add(anchorPane);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack(MouseEvent mouseEvent) {
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

    private AnchorPane createDataAnchorPane(String title, String author, String isbn) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(38.0);
        anchorPane.setPrefWidth(446.0);
        anchorPane.setStyle("-fx-background-color: lightgray; -fx-border-color: darkgray;");

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
}