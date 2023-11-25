package library.library.homePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.library.LibraryApplication;

import java.io.IOException;

public class InterfaceController
{
    @FXML
    private ComboBox<String> dropdown;

    @FXML
    private TextField search;

    @FXML
    void Select(ActionEvent event) {
        String op = dropdown.getSelectionModel().getSelectedItem();

        if (op != null) {
            switch (op) {
                case "Perfil":
                    try {
                        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/AccountInterface.fxml"));
                        Scene newScene = new Scene(loader.load());

                        Stage currentStage = (Stage) dropdown.getScene().getWindow();
                        currentStage.setScene(newScene);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Mis Libros":
                    System.out.println("mis libross");
                    break;

                default:
                    // Manejar otras opciones según sea necesario
                    break;
            }
        }
    }
    @javafx.fxml.FXML
    public void initialize() {

        ObservableList<String> list = FXCollections.observableArrayList("Perfil", "Mis Libros");
        dropdown.setItems(list);
    }}

