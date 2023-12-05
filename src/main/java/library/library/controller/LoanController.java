package library.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Controller class for managing user loans.
 * Handles UI elements and user interactions related to loan information.
 */
public class LoanController implements Initializable {

    @FXML
    private Text back;

    @FXML
    private VBox container;

    /**
     * Initializes the LoanController, populating loan information for the current user.
     *
     * @param url            The location used to resolve relative paths for the root object,
     *                       or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root
     *                       object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ResultSet rs = null;
        try {
            // Get the current user's email and retrieve their loans
            String query = LibraryApplication.getSession().getEmail();
            ResultSet st;
            st = DatabaseController.executeQuery("SELECT * FROM estudiantes WHERE correo_electronico = '" + query + "'");
            if(st.next()){
                rs = DatabaseController.executeQuery("SELECT * FROM Autores\n" +
                        "INNER JOIN main.LIBRO L on L.ISBN = Autores.ISBN\n" +
                        "INNER JOIN main.PRESTAMO P on L.ISBN = P.ISBN\n" +
                        "WHERE P.Clave_Usuario = " + st.getString("Clave_Usuario")
                );

                // Create and add UI elements for each loan
                while (rs.next()) {
                    AnchorPane anchorPane = createDataAnchorPane(rs.getString("Titulo"), rs.getString("NombreAutor"), rs.getString("ISBN"));
                    container.getChildren().add(anchorPane);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an AnchorPane with loan information.
     *
     * @param title  The title of the book.
     * @param author The author of the book.
     * @param isbn   The ISBN of the book.
     * @return The created AnchorPane.
     */
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

    /**
     * Handles the event when the user clicks the "Back" button.
     * Returns to the main interface view.
     *
     * @param event The mouse event triggered by the user.
     */
    @FXML
    void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/Interface.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtain the current Stage and change its scene
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}