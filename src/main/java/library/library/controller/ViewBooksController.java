package library.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import library.library.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewBooksController {

    @FXML
    private TableView<Book> loanTable;

    @FXML
    private TableColumn<Book, Integer> isbnColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> yearColumn;

    @FXML
    private TableColumn<Book, String> floorColumn;

    @FXML
    private TableColumn<Book, String> shelfColumn;

    @FXML
    private TextField searchField;

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            ResultSet rs = DatabaseController.executeQuery("SELECT * FROM Autores\n" +
                    "INNER JOIN main.LIBRO L on L.ISBN = Autores.ISBN\n" +
                    "INNER JOIN main.PRESTAMO P on L.ISBN = P.ISBN;");
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

            loanTable.setItems(books);

            FilteredList<Book> filteredData = new FilteredList<>(books, b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(book -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    return book.getTitle().toLowerCase().contains(lowerCaseFilter) ||
                            book.getYear().toLowerCase().contains(lowerCaseFilter) ||
                            book.getFloor().toLowerCase().contains(lowerCaseFilter) ||
                            book.getShelf().toLowerCase().contains(lowerCaseFilter) ||
                            book.getIsbn().toString().contains(lowerCaseFilter);
                });
            });

            SortedList<Book> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(loanTable.comparatorProperty());
            loanTable.setItems(sortedData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
