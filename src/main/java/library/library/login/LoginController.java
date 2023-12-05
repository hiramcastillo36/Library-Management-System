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

/**
 * Controller class for the login view.
 * Manages the UI elements and user interactions on the login view.
 */
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

    /**
     * Handles the sign-in action.
     * Validates the user input, performs sign-in, and changes the scene accordingly.
     */
    @FXML
    protected void signIn() {
        // check if the email and password are not empty
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
                LibraryApplication.changeScene("adminMenu");
        }
    }

    /**
     * Validates the input fields.
     * Checks if the email and password fields are not empty.
     * Displays error messages if needed.
     *
     * @return True if validation fails, false otherwise.
     */
    private boolean validateInput() {
        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            if(email.getText().isEmpty())
                errorEmail.setText("Campo requerido");
            if (password.getText().isEmpty()) {
                errorPassword.setText("Campo requerido");
            }
            return true;
        }
        errorEmail.setText("");
        errorPassword.setText("");
        accountNotFound.setText("");
        return false;
    }

    /**
     * Handles the sign-up action.
     * Changes the scene to the user form interface for registration.
     */
    @FXML
    protected void signUp() {
        LibraryApplication.changeScene("UserFormInterface");
    }

    /**
     * Handles the "Go Back" action.
     * Changes the scene back to the main interface.
     *
     * @param event The mouse event triggered by the user.
     */
    @FXML
    void goBack(MouseEvent event) {
        try {
            // Load the previous scene
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/Interface.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Get the current stage and change its scene
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace(); // Basic exception handling, adjust as needed
        }
    }
}