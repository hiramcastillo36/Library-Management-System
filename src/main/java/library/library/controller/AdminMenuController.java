package library.library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;

import java.io.IOException;

public class AdminMenuController {
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button profileMenu;

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
}