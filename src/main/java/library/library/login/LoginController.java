package library.library.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;

import java.io.IOException;

public class LoginController {

    @FXML
    private Text back;

    @FXML
    private Button button;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorPassword;

    @FXML
    private Label accountNotFound;

    @FXML
    protected void signIn() {
        // check if the email and password is not empty
        if (validateInput()) {
            return;
        }
        int result = LibraryApplication.signIn(email.getText(), password.getText());
        if (result == -1)
            accountNotFound.setText("Cuenta no encontrada");
        else if (result == 0)
            errorPassword.setText("Contraseña incorrecta");
        else if (result == 1){
            String accountType = LibraryApplication.getSession().getAccountType();
            if(accountType.equals("Estudiante"))
                LibraryApplication.changeScene("Interface");
            else if(accountType.equals("Admin"))
                LibraryApplication.changeScene("admin-dashboard");
        }
    }
    private boolean validateInput() {
        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            if(email.getText().isEmpty())
                errorEmail.setText("No puede estar vacío");
            if (password.getText().isEmpty()) {
                errorPassword.setText("No puede estar vacío");
            }
            return true;
        }
        errorEmail.setText("");
        errorPassword.setText("");
        accountNotFound.setText("");
        return false;
    }

    @FXML
    protected void signUp() {

        if(validateInput())
            return;

        int result = LibraryApplication.signUp(email.getText(), password.getText());
        if(result == 0)
            errorEmail.setText("Email ya existe");
        else if(result == 1){
            String accountType = LibraryApplication.getSession().getAccountType();
            if(accountType.equals("Usuario"))
                LibraryApplication.changeScene("Interface");
            else if(accountType.equals("Admin"))
                LibraryApplication.changeScene("admin-dashboard");
        }
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
            e.printStackTrace(); // Manejo básico de excepciones, ajusta según sea necesario
        }
    }

}
