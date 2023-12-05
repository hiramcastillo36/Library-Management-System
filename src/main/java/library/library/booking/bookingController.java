package library.library.booking;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.SortedMap;


/**
 * Controller class for the booking interface.
 * Manages the display of books, filtering, and navigation.
 */
public class bookingController implements Initializable {

    @FXML
    private Text back;

    @FXML
    private TableView<Book> table = new TableView<>();

    @FXML
    private TextField searchBar = new TextField();

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

    ObservableList<Book> books = FXCollections.observableArrayList();

    /**
     * Initializes the booking interface with book data and sets up filtering.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources for the root object, or null if none.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Libro");
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
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
                    } else return book.getISBN().contains(lowerCaseFilter);
                });
            });

            SortedList<Book> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(filteredData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates back to the administrator menu.
     *
     * @param event The mouse event triggering the navigation.
     */
    @FXML
    void goBack(MouseEvent event) {
        try {
            // Load the new scene (in this case, the previous scene)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/AdminMenu.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Get the current Stage and set its scene
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}