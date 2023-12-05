package library.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for the main library application.
 * Handles UI elements and user interactions related to the main view.
 */
public class LibraryController {
    @FXML
    private Label welcomeText;

    /**
     * Event handler for the "Hello" button click.
     * Updates the welcome text when the button is clicked.
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}