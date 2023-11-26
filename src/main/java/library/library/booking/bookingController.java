package library.library.booking;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.library.controller.DatabaseController;
import library.library.models.Book;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class bookingController implements Initializable {
    @FXML
    private TableView<Book> table = new TableView<>();

    @FXML
    private TableColumn <Book, Integer> isbnColumn;

    @FXML
    private TableColumn <Book, String> titleColumn;

    @FXML
    private TableColumn <Book, String> yearColumn;

    // piso columna
    @FXML
    private TableColumn <Book, String> floorColumn;

    // estante columna
    @FXML
    private TableColumn <Book, String> shelfColumn;

    ObservableList<Book> books = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Libro");
            while (rs.next()) {
                Integer isbn = rs.getInt("ISBN");
                String title = rs.getString("Titulo");
                String year = rs.getString("A_publicacion");
                String floor = rs.getString("Piso");
                String shelf = rs.getString("Estante");

                books.add(new Book(isbn, title, year, floor, shelf));
            }

            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
            shelfColumn.setCellValueFactory(new PropertyValueFactory<>("shelf"));

            table.setItems(books);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
