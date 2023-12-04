package library.library.adminDashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Booking;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDashboardController {
    @FXML
    private Text back;

    @FXML
    private Button searchBook;

    @FXML
    private TableView<Booking> tableBooking = new TableView<>();

    @FXML
    private TextField isbnBook;

    @FXML
    private TextField studentEmail;

    @FXML
    private Button confirm;

    @FXML
    private Label isbnError;

    @FXML
    private Label studentEmailError;

    @FXML
    private TableColumn<Booking, String> isbnColumn;
    @FXML
    private TableColumn<Booking, Integer> userColumn;

    @FXML
    private TableColumn<Booking, Integer> dayColumn;
    @FXML
    private TableColumn<Booking, Integer> monthColumn;
    @FXML
    private TableColumn<Booking, Integer> yearColumn;

    private Booking booking;
    ObservableList<Booking> bookings = FXCollections.observableArrayList();

    @FXML
    protected void searchBook() {
        // check if the email and password is not empty
        if (validateInput()) {
            return;
        }
        try {
            String fetchBooking = "SELECT * FROM PRESTAMO WHERE ISBN = ? AND Clave_Usuario = ? LIMIT 1";
            PreparedStatement statement = DatabaseController.getConnection().prepareStatement(fetchBooking);
            statement.setString(1, isbnBook.getText());
            statement.setString(2, studentEmail.getText());
            ResultSet rs = DatabaseController.executeQuery(statement);

            bookings.clear();
            if (rs.next()) {
                booking = new Booking(rs.getString("ISBN"),
                        rs.getInt("Clave_Usuario"),
                        rs.getString("NSS"),
                        rs.getInt("Dia_Prestamo"),
                        rs.getInt("Mes_Prestamo"),
                        rs.getInt("Ano_Prestamo")
                );
                confirm.setDisable(false);
                bookings.add(booking);
            } else {
                confirm.setDisable(false);
                booking = null;
            }
            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            userColumn.setCellValueFactory(new PropertyValueFactory<>("claveUsuario"));
            dayColumn.setCellValueFactory(new PropertyValueFactory<>("diaPrestamo"));
            monthColumn.setCellValueFactory(new PropertyValueFactory<>("mesPrestamo"));
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("añoPrestamo"));
            tableBooking.setItems(bookings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateInput() {
        if (isbnBook.getText().isEmpty() || studentEmail.getText().isEmpty()) {
            if (isbnBook.getText().isEmpty())
                isbnError.setText("Ingrese un ISBN");
            if (studentEmail.getText().isEmpty())
                studentEmailError.setText("Ingrese una clave de usuario");
            return true;
        }
        isbnError.setText("");
        studentEmailError.setText("");
        return false;
    }

    public void confirmBooking(ActionEvent actionEvent) {
        try {
            String deleteBooking = "DELETE FROM PRESTAMO WHERE ISBN = ? AND Clave_Usuario = ?";
            PreparedStatement statement = DatabaseController.getConnection().prepareStatement(deleteBooking);
            Integer clave = booking.getClaveUsuario();
            statement.setString(1, booking.getIsbn());
            statement.setString(2, clave.toString());
            DatabaseController.executeInsert(statement);
            bookings.remove(booking);
            isbnBook.setText("");
            studentEmail.setText("");
            confirm.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goBack(MouseEvent event) {
        try {
            // Cargar la nueva escena (en este caso, la escena anterior)
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("view/adminMenu.fxml"));
            Scene previousScene = new Scene(loader.load());

            // Obtener el Stage actual y cambiar su escena
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
