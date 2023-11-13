package library.library.Interface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InterfaceController
{
    @FXML
    private ComboBox<String> dropdown;

    @FXML
    private Label label;

    @FXML
    private TextField search;

    @FXML
    void Select(ActionEvent event) {

    }

    @javafx.fxml.FXML
    public void initialize() {

        ObservableList<String> list = FXCollections.observableArrayList("Perfil", "Mis Libros");
        dropdown.setItems(list);
    }}

