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

public class AdminProfileController implements Initializable {
    @FXML
    private Text back;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField accountType;

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

    public void goBack(MouseEvent mouseEvent) {
        LibraryApplication.changeScene("adminMenu");
    }
}
