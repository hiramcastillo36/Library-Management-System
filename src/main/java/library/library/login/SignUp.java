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
 * Controller class for the sign-up view.
 * Manages the UI elements and user interactions on the sign-up view.
 */
public class SignUp {
    @FXML
    private Text back;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField claveUnica;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidoPaterno;

    @FXML
    private TextField apellidoMaterno;

    @FXML
    private PasswordField password;

    @FXML
    private Label claveError;

    @FXML
    private Label errorPassword;

    @FXML
    private Label errorNombre;

    @FXML
    private Label errorApellidoPaterno;

    @FXML
    private Label errorApellidoMaterno;


    @FXML
    private Label account;

    /**
     * Handles the sign-up action.
     * Validates the user input, performs sign-up, and changes the scene accordingly.
     */
    @FXML
    protected void signUp() {
        if(validateInput())
            return;

        int result = LibraryApplication.signUp( claveUnica.getText(),
                password.getText(),
                nombre.getText(),
                apellidoPaterno.getText(),
                apellidoMaterno.getText()
        );
        if(result == 0)
            claveError.setText("Cuenta ya existe");
        else if(result == 1){
            String accountType = LibraryApplication.getSession().getAccountType();
            if(accountType.equals("Estudiante"))
                LibraryApplication.changeScene("Interface");
            else if(accountType.equals("Admin"))
                LibraryApplication.changeScene("adminMenu");
        }
    }

    /**
     * Validates the input fields.
     * Checks if the required fields are not empty.
     * Displays error messages if needed.
     *
     * @return True if validation fails, false otherwise.
     */
    private boolean validateInput() {
        boolean error = false;
        if (claveUnica.getText().isEmpty() ||
                password.getText().isEmpty() ||
                nombre.getText().isEmpty() ||
                apellidoPaterno.getText().isEmpty() ||
                apellidoMaterno.getText().isEmpty()) {
            if(claveUnica.getText().isEmpty()){
                claveError.setText("Campo vacío");
            }
            if(password.getText().isEmpty()){
                errorPassword.setText("Campo vacío");
            }
            if(nombre.getText().isEmpty()){
                errorNombre.setText("Campo vacío");
            }
            if(apellidoPaterno.getText().isEmpty()){
                errorApellidoPaterno.setText("Campo vacío");
            }
            if(apellidoMaterno.getText().isEmpty()){
                errorApellidoMaterno.setText("Campo vacío");
            }
            return true;
        }
        claveError.setText("");
        errorPassword.setText("");
        errorNombre.setText("");
        errorApellidoPaterno.setText("");
        errorApellidoMaterno.setText("");
        return false;
    }

    /**
     * Handles the "Go Back" action.
     * Changes the scene back to the login view.
     *
     * @param event The mouse event triggered by the user.
     */
    @FXML
    void goBack(MouseEvent event) {
        LibraryApplication.changeScene("Login");
    }
}