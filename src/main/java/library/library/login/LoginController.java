package library.library.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import library.library.LibraryApplication;

public class LoginController {

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
        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            System.out.println("Email or password is empty");
            if(email.getText().isEmpty())
                errorEmail.setText("No puede estar vacío");
            if (password.getText().isEmpty()) {
                errorPassword.setText("No puede estar vacío");
            }
            return;
        } else {
            errorEmail.setText("");
            errorPassword.setText("");
            accountNotFound.setText("");
        }
        int result = LibraryApplication.signIn(email.getText(), password.getText());
        if(result == -1)
            accountNotFound.setText("Cuenta no encontrada");

        else if(result == 0)
            errorPassword.setText("Contraseña incorrecta");
        else if(result == 1)
            LibraryApplication.changeScene("Interface");

    }

    @FXML
    protected void signUp() {
        System.out.println("Email: " + email.getText());
        System.out.println("Password: " + password.getText());
        LibraryApplication.signUp(email.getText(), password.getText());
    }
}
