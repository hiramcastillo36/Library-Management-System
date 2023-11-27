package library.library.booking;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import library.library.controller.DatabaseController;
import library.library.models.Book;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.SortedMap;

public class bookingController implements Initializable {
    @FXML
    private TableView<Book> table = new TableView<>();

    @FXML
    private TextField searchBar = new TextField();

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

            FilteredList<Book> filteredData = new FilteredList<>(books, b -> true);

            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(book -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (book.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (book.getYear().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (book.getFloor().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (book.getShelf().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (book.getIsbn().toString().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<Book> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(filteredData);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
