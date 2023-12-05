package library.library.adminProfile;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Account;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for the administrator profile interface.
 * Manages the display of administrator profile information and navigation.
 */
public class AdminProfileController implements Initializable {

    @FXML
    private Text back;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField accountType;

    /**
     * Initializes the administrator profile with user information.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources for the root object, or null if none.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = null;
        try {
            Account account = LibraryApplication.getSession();
            String correoUsuario = account.getEmail();
            rs = DatabaseController.executeQuery("SELECT * FROM PERSONAL" +
                    " WHERE correo_electronico=\"" + correoUsuario + "\";");
            String nombre = rs.getString("Nombre_Personal");
            name.setText(nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Account account = LibraryApplication.getSession();
        email.setText(account.getEmail());
        accountType.setText(account.getAccountType());
    }

    /**
     * Navigates back to the administrator menu.
     *
     * @param mouseEvent The mouse event triggering the navigation.
     */
    public void goBack(MouseEvent mouseEvent) {
        LibraryApplication.changeScene("adminMenu");
    }
}