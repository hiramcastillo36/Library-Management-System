package library.library.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    protected void signIn() {
        // check if the email and password is not empty
        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            System.out.println("Email or password is empty");
            return;
        }
        LibraryApplication.signIn(email.getText(), password.getText());
    }

    @FXML
    protected void signUp() {
        System.out.println("Email: " + email.getText());
        System.out.println("Password: " + password.getText());
        LibraryApplication.signUp(email.getText(), password.getText());
    }
}
