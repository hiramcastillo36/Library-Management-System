package library.library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import library.library.LibraryApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    @FXML
    private void initialize() {
        // Llamada a métodos para cargar el total de libros prestados y usuarios registrados
        loadTotalBooks();
        loadTotalUsers();
    }

    // Método para cargar el total de libros prestados
    private void loadTotalBooks() {
        try {
            // Realizar la consulta SQL para obtener el número de libros prestados
            ResultSet resultSet = DatabaseController.executeQuery("SELECT COUNT(*) AS total FROM PRESTAMO");
            if (resultSet.next()) {
                int totalBooks = resultSet.getInt("total");
                totalBooksLabel.setText(String.valueOf(totalBooks));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar el total de usuarios registrados
    private void loadTotalUsers() {
        try {
            // Realizar la consulta SQL para obtener el número de usuarios registrados
            ResultSet resultSet = DatabaseController.executeQuery("SELECT COUNT(*) AS total FROM Estudiantes");
            if (resultSet.next()) {
                int totalUsers = resultSet.getInt("total");
                totalUsersLabel.setText(String.valueOf(totalUsers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleButton1(ActionEvent event) {
        LibraryApplication.changeScene("bookingDashboard");
    }

    @FXML
    void handleButton2(ActionEvent event) {
        LibraryApplication.changeScene("booksDashboard");
    }

    @FXML
    void adminProfile(ActionEvent event) {
        LibraryApplication.changeScene("adminProfile");
    }

    @FXML
    void viewBooks(ActionEvent event) {
        LibraryApplication.changeScene("ViewBooks");
    }

}