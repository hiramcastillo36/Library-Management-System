package library.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.library.models.Account;
import library.library.util.Session;

import java.io.IOException;

/**
 * Main class for the Library Application.
 */
public class LibraryApplication extends Application {

    /**
     * The session manager for handling user sessions.
     */
    private static final Session session = new Session();

    /**
     * The primary stage for the JavaFX application.
     */
    private static Stage stage;

    /**
     * Launches the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Starts the JavaFX application by setting up the primary stage and loading the main interface.
     *
     * @param stage The primary stage provided by the JavaFX framework.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        LibraryApplication.stage = stage;
        Parent root = FXMLLoader.load(LibraryApplication.class.getResource("view/Interface.fxml"));
        stage.setTitle("Library");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /**
     * Changes the scene in the application to the specified FXML file.
     *
     * @param s The name of the FXML file (without the file extension) to load.
     */
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

    /**
     * Attempts to sign in a user with the provided email and password.
     *
     * @param email    The email of the user.
     * @param password The password for the user.
     * @return -1 if the email doesn't exist, 0 if the password is incorrect, 1 if signed in successfully.
     */
    public static int signIn(String email, String password) {
        return session.signIn(email, password);
    }

    /**
     * Attempts to sign up a new user with the provided information.
     *
     * @param clave           The unique identifier for the user.
     * @param password        The password for the user.
     * @param nombre          The first name of the user.
     * @param apellidoPaterno The last name of the user.
     * @param apellidoMaterno The second last name of the user.
     * @return 0 if the account already exists, 1 if signed up successfully.
     */
    public static int signUp(String clave, String password, String nombre, String apellidoPaterno, String apellidoMaterno) {
        return session.signUp(clave, password, nombre, apellidoPaterno, apellidoMaterno);
    }

    /**
     * Signs out the current user and changes the scene to the main interface.
     */
    public static void signOut() {
        session.signOut();
    }

    /**
     * Gets the current user session.
     *
     * @return The current user session or null if no user is signed in.
     */
    public static Account getSession() {
        return session.getCurrentUser();
    }
}