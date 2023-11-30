package library.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import library.library.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewBooksController {

    @FXML
    private VBox container;

    @FXML
    private TextField searchField;

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Autores\n" +
                    "INNER JOIN main.LIBRO L on L.ISBN = Autores.ISBN\n" +
                    "INNER JOIN main.PRESTAMO P on L.ISBN = P.ISBN");

            while (rs.next()) {
                AnchorPane anchorPane = createDataAnchorPane(rs.getString("Titulo"),
                        rs.getString("NombreAutor"), rs.getString("ISBN"),
                        rs.getString("Clave_Usuario"));
                container.getChildren().add(anchorPane);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private AnchorPane createDataAnchorPane(String title, String author, String isbn, String clave_usuario) {
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
