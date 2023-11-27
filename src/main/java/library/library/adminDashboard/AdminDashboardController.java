package library.library.adminDashboard;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import library.library.models.Book;

public class AdminDashboardController {

    @FXML
    private Button button;

    @FXML
    private TableView<Book> tableBooks = new TableView<>();

}
