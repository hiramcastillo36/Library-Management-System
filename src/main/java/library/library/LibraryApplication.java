package library.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.library.models.Account;
import library.library.util.Session;
import java.io.IOException;
import java.sql.SQLException;

public class LibraryApplication extends Application {
    private static Session session = new Session();

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Library");
        Parent root = FXMLLoader.load(getClass().getResource("view/interface.fxml"));
        stage.setTitle("Library");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene(String s) {
        try {
            Parent root = FXMLLoader.load(LibraryApplication.class.getResource("view/" + s + ".fxml"));
            stage.setTitle("Library");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void signIn(String email, String password) {
        int result = session.signIn(email, password);
        if (result == 1) {

            changeScene("Interface");
        } else {
            System.out.println("Error");
        }
    }

    public static void signUp(String email, String password) {
        session.signUp(email, password);
    }

    public static void signOut() {
        session.signOut();
    }

    public static Account getSession(){
        return session.getCurrentUser();
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}