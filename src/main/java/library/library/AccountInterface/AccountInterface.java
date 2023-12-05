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

/**
 * Controller class for the user account interface.
 * Manages user account details display and navigation.
 */
public class AccountInterface implements Initializable {

    @FXML
    private Text back;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField accountType;

    /**
     * Handles the action when the user wants to go back to the previous scene.
     *
     * @param event The mouse event triggering the action.
     */
    @FXML
    void goBack(MouseEvent event) {
        try {
            // Load the new scene (in this case, the previous scene)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/Interface.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Get the current Stage and change its scene
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace(); // Basic exception handling, adjust as needed
        }
    }

    /**
     * Initializes the controller with user account details.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources for the root object, or null if none.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = null;
        try {
            Account account = LibraryApplication.getSession();
            String userEmail = account.getEmail();
            rs = DatabaseController.executeQuery("SELECT * FROM Estudiantes" +
                    " WHERE correo_electronico=\"" + userEmail + "\";");

            if (rs.next()) {
                String userName = rs.getString("Nombre_Usuario");
                name.setText(userName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Account account = LibraryApplication.getSession();
        email.setText(account.getEmail());
        accountType.setText(account.getAccountType());
    }
}