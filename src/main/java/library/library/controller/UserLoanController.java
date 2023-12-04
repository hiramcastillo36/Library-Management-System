package library.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import library.library.LibraryApplication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class UserLoanController {
    @FXML
    private VBox container;

    @FXML
    private Text back;
    @FXML
    private TextField searchField;

    @FXML
    private void initialize() {
        ResultSet rs = null;
        try {
            String query = LibraryApplication.getSession().getEmail();
            ResultSet st;
            PreparedStatement userStatent = DatabaseController.getConnection().prepareStatement("SELECT * FROM Estudiantes WHERE correo_electronico = ?");
            userStatent.setString(1, query);
            st = userStatent.executeQuery();
            if(st.next()){
                String unborrowedBooks = "SELECT * FROM ( SELECT L2.* FROM main.LIBRO L2 LEFT JOIN main.PRESTAMO P ON L2.ISBN = P.ISBN AND P.Clave_Usuario = ? WHERE P.ISBN IS NULL ) as L INNER JOIN main.AUTORES A on A.ISBN = L.ISBN";
                PreparedStatement statement = DatabaseController.getConnection().prepareStatement(unborrowedBooks);
                statement.setString(1, st.getString("Clave_Usuario"));
                rs = statement.executeQuery();

                while (rs.next()) {
                    AnchorPane anchorPane = createDataAnchorPane(   rs.getString("Titulo"),
                                                                    rs.getString("NombreAutor"),
                                                                    rs.getString("ISBN"));
                    container.getChildren().add(anchorPane);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack(MouseEvent mouseEvent) {
        LibraryApplication.changeScene("Interface");
    }

    private AnchorPane createDataAnchorPane(String title, String author, String isbn) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(38.0);
        anchorPane.setPrefWidth(446.0);
        anchorPane.setStyle("-fx-background-color: lightgray; -fx-border-color: darkgray;");

        Button borrowedBook = new Button("Pedir Libro");
        borrowedBook.setOnAction(event -> handleborrowedBook(isbn));
        borrowedBook.setLayoutX(414.0);
        borrowedBook.setLayoutY(6.0);
        
        Label labelLibro = new Label(title);
        labelLibro.setLayoutX(14.0);
        labelLibro.setLayoutY(10.0);

        Label labelAutor = new Label(author);
        labelAutor.setLayoutX(200.0);
        labelAutor.setLayoutY(10.0);

        Label labelISBN = new Label(isbn);
        labelISBN.setLayoutX(300.0);
        labelISBN.setLayoutY(10.0);

        anchorPane.getChildren().addAll(labelLibro, labelAutor, labelISBN, borrowedBook);
        anchorPane.setUserData(isbn);

        return anchorPane;
    }

    private void handleborrowedBook(String isbn) {
        LocalDate localDate = LocalDate.now();
        LocalDate currentDate
                = LocalDate.parse(localDate.toString());

        Integer day = currentDate.getDayOfMonth();
        Integer month = currentDate.getMonthValue();
        Integer year = currentDate.getYear();

        ResultSet rs = null;
        try {
            String query = LibraryApplication.getSession().getEmail();
            ResultSet st;
            PreparedStatement userStatent = DatabaseController.getConnection().prepareStatement("SELECT * FROM Estudiantes WHERE correo_electronico = ?");
            userStatent.setString(1, query);
            st = userStatent.executeQuery();
            if(st.next()){
                    String addBorrowedBook = "INSERT INTO Prestamo(ISBN, Clave_Usuario, NSS, Dia_Prestamo, Mes_Prestamo, Ano_Prestamo) VALUES(?,?,?,?,?,?);";
                    PreparedStatement statementBorrowed = DatabaseController.getConnection().prepareStatement(addBorrowedBook);
                    statementBorrowed.setString(1, isbn);
                    statementBorrowed.setString(2, st.getString("Clave_Usuario"));
                    statementBorrowed.setString(3, "531-679-567");
                    statementBorrowed.setString(4, day.toString());
                    statementBorrowed.setString(5, month.toString());
                    statementBorrowed.setString(6, year.toString());
                    DatabaseController.executeInsert(statementBorrowed);
                    container.getChildren().removeIf(node -> node.getUserData().equals(isbn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}