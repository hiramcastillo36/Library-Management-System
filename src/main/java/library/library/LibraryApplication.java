package library.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.library.models.Account;
import library.library.util.Session;
import java.io.IOException;

public class LibraryApplication extends Application {
    private static final Session session = new Session();

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        LibraryApplication.stage = stage;
        Parent root = FXMLLoader.load(LibraryApplication.class.getResource("view/Interface.fxml"));
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

    public static int signIn(String email, String password) {
        return session.signIn(email, password);
    }

    public static int signUp(   String clave,
                                String password,
                                String nombre,
                                String apellidoPaterno,
                                String apellidoMaterno

    ) {
        return session.signUp(clave, password, nombre, apellidoPaterno, apellidoMaterno);
    }

    public static void signOut() {
        session.signOut();
    }

    public static Account getSession(){
        return session.getCurrentUser();
    }

    public static void main(String[] args) {
        launch();
    }
}