package library.library.AccountInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Account;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountInterface implements Initializable {

    @FXML
    private Text back;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField accountType;

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
            e.printStackTrace(); // Manejo básico de excepciones, ajusta según sea necesario
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = null;
        try {
            Account account = LibraryApplication.getSession();
            String correoUsuario = account.getEmail();
            rs = DatabaseController.executeQuery("SELECT * FROM Estudiantes" +
                    " WHERE correo_electronico=\"" + correoUsuario + "\";");
                String nombre = rs.getString("Nombre_Usuario");
                name.setText(nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Account account = LibraryApplication.getSession();
        email.setText(account.getEmail());
        accountType.setText(account.getAccountType());
    }
}
