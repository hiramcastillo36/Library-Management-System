package library.library.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import library.library.LibraryApplication;

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
}
