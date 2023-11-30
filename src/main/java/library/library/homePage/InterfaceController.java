package library.library.homePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Book;

import java.io.IOException;
import java.sql.ResultSet;

public class InterfaceController
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
                    try {
                        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/Loan.fxml"));
                        Scene newScene = new Scene(loader.load());

                        Stage currentStage = (Stage) dropdown.getScene().getWindow();
                        currentStage.setScene(newScene);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Cerrar Sesión":
                    LibraryApplication.signOut();
                default:
                    // Manejar otras opciones según sea necesario
                    break;
            }
        }
    }
    @javafx.fxml.FXML
    public void initialize() {
        ObservableList<String> list;
        System.out.println(LibraryApplication.getSession());
        if(LibraryApplication.getSession() == null) {
            signin.setVisible(true);
            dropdown.setVisible(false);
        } else {
            signin.setVisible(false);
            dropdown.setVisible(true);
            list = FXCollections.observableArrayList("Perfil", "Mis Libros", "Cerrar Sesión");
            dropdown.setItems(list);
        }

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

            table.setItems(books);

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

    public void signin(MouseEvent mouseEvent) {
        LibraryApplication.changeScene("Login");
    }
}

