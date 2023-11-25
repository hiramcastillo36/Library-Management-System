package library.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.library.util.Sessions;
import java.io.IOException;
import java.sql.SQLException;

public class LibraryApplication extends Application {
    private static Sessions sessions = new Sessions();

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Library");
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
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
        int result = sessions.signIn(email, password);
        if (result == 1) {
            changeScene("Interface");
        } else {
            System.out.println("Error");
        }
    }

    public static void signUp(String email, String password) {
        sessions.signUp(email, password);
    }

    public static Sessions getSession(){
        return sessions;
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}