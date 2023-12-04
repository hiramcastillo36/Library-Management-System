package library.library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import library.library.LibraryApplication;

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