package library.library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import library.library.LibraryApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller class for the administrator menu interface.
 * Manages actions such as navigation, displaying total books and users, and handling sign out.
 */
public class AdminMenuController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button profileMenu;

    @FXML
    private Text back;

    @FXML
    private Button viewBook;

    @FXML
    private Label totalBooksLabel;

    @FXML
    private Label totalUsersLabel;

    /**
     * Initializes the administrator menu by loading total books and total users.
     */
    @FXML
    private void initialize() {
        loadTotalBooks();
        loadTotalUsers();
    }

    /**
     * Loads the total number of books and updates the total books label.
     */
    private void loadTotalBooks() {
        try {
            ResultSet resultSet = DatabaseController.executeQuery("SELECT COUNT(*) AS total FROM PRESTAMO");
            if (resultSet.next()) {
                int totalBooks = resultSet.getInt("total");
                totalBooksLabel.setText(String.valueOf(totalBooks));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the total number of users and updates the total users label.
     */
    private void loadTotalUsers() {
        try {
            ResultSet resultSet = DatabaseController.executeQuery("SELECT COUNT(*) AS total FROM Estudiantes");
            if (resultSet.next()) {
                int totalUsers = resultSet.getInt("total");
                totalUsersLabel.setText(String.valueOf(totalUsers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when Button 1 is clicked, changing the scene to the booking dashboard.
     *
     * @param event The action event triggered by clicking Button 1.
     */
    @FXML
    void handleButton1(ActionEvent event) {
        LibraryApplication.changeScene("bookingDashboard");
    }

    /**
     * Handles the action when Button 2 is clicked, changing the scene to the books dashboard.
     *
     * @param event The action event triggered by clicking Button 2.
     */
    @FXML
    void handleButton2(ActionEvent event) {
        LibraryApplication.changeScene("booksDashboard");
    }

    /**
     * Handles the action when the admin profile button is clicked, changing the scene to the admin profile.
     *
     * @param event The action event triggered by clicking the admin profile button.
     */
    @FXML
    void adminProfile(ActionEvent event) {
        LibraryApplication.changeScene("adminProfile");
    }

    /**
     * Handles the action when the view books button is clicked, changing the scene to view books.
     *
     * @param event The action event triggered by clicking the view books button.
     */
    @FXML
    void viewBooks(ActionEvent event) {
        LibraryApplication.changeScene("ViewBooks");
    }

    /**
     * Handles the action when signing out, triggering the sign-out functionality.
     *
     * @param mouseEvent The mouse event triggering the sign-out action.
     */
    @FXML
    public void signout(MouseEvent mouseEvent) {
        LibraryApplication.signOut();
    }
}