package library.library.homePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Controller class for the main interface.
 * Manages the UI elements and user interactions on the main interface.
 */
public class InterfaceController implements Initializable
{
    @FXML
    private ComboBox<String> dropdown;

    @FXML
    private TableView<Book> table = new TableView<>();

    @FXML
    private TextField searchBar = new TextField();

    @FXML
    private TableColumn<Book, String> isbnColumn;

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

    @FXML
    private Label signin;

    ObservableList<Book> books = FXCollections.observableArrayList();

    /**
     * Handles the selection of an item from the dropdown menu.
     * Performs different actions based on the selected option.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    void Select(ActionEvent event) {
        String op = dropdown.getSelectionModel().getSelectedItem();

        if (op != null) {
            switch (op) {
                case "Perfil":
                    // Change the scene to the account interface view
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
                    // Change the scene to the loan view
                    try {
                        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/Loan.fxml"));
                        Scene newScene = new Scene(loader.load());

                        Stage currentStage = (Stage) dropdown.getScene().getWindow();
                        currentStage.setScene(newScene);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Realizar prestamo":
                    // Change the scene to the user loan view
                    try {
                        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/UserLoan.fxml"));
                        Scene newScene = new Scene(loader.load());

                        Stage currentStage = (Stage) dropdown.getScene().getWindow();
                        currentStage.setScene(newScene);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Cerrar Sesión":
                    // Sign out from the application
                    LibraryApplication.signOut();
                default:
                    // Handle other options as needed
                    break;
            }
        }
    }

    /**
     * Handles the mouse click event for the "Sign In" label.
     * Changes the scene to the login view.
     *
     * @param mouseEvent The mouse event triggered by the user.
     */
    public void signin(MouseEvent mouseEvent) {
        LibraryApplication.changeScene("Login");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list;
        if(LibraryApplication.getSession() == null) {
            signin.setVisible(true);
            dropdown.setVisible(false);
        } else {
            signin.setVisible(false);
            dropdown.setVisible(true);
            list = FXCollections.observableArrayList("Perfil", "Mis Libros","Realizar prestamo", "Cerrar Sesión");
            dropdown.setItems(list);
        }

        ResultSet rs = null;
        try {
            // Retrieve book information from the database
            rs = DatabaseController.executeQuery("SELECT * FROM Libro");
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String title = rs.getString("Titulo");
                String year = rs.getString("A_publicacion");
                String floor = rs.getString("Piso");
                String shelf = rs.getString("Estante");

                books.add(new Book(isbn, title, year, floor, shelf));
            }

            // Set up table columns and populate the table
            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
            shelfColumn.setCellValueFactory(new PropertyValueFactory<>("shelf"));

            table.setItems(books);

            // Set up filtering for the table
            FilteredList<Book> filteredData = new FilteredList<>(books, b -> true);

            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(book -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    return book.getTitle().toLowerCase().contains(lowerCaseFilter) ||
                            book.getYear().toLowerCase().contains(lowerCaseFilter) ||
                            book.getFloor().toLowerCase().contains(lowerCaseFilter) ||
                            book.getShelf().toLowerCase().contains(lowerCaseFilter) ||
                            book.getIsbn().contains(lowerCaseFilter);
                });
            });

            SortedList<Book> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(filteredData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}