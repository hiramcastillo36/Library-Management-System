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

import java.io.IOException;

public class addBookController {

    @FXML
    private Text back;

    @FXML
    private Button Enviar;

    @FXML
    private TextField Estante;

    @FXML
    private TextField ISBN;

    @FXML
    private TextField Piso;

    @FXML
    private TextField Titulo;

    @FXML
    private TextField fecha;
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
}
