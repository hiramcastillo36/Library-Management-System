package library.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.library.controller.DatabaseController;
import java.io.IOException;
import java.sql.SQLException;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("view/AccountInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Library");
        stage.setScene(scene);
        stage.show();
    }
    

    public static void main(String[] args) throws SQLException {
        DatabaseController.getAutores();
        DatabaseController.getAutores();
        launch();
    }
}