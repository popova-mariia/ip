package mariiachatbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mariiachatbot.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private MariiaChatbot mariiaChatbot = new MariiaChatbot("./src/main/java/data/mariia.txt");

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "FXMLLoader failed to initialize";

            AnchorPane ap = fxmlLoader.load();
            MainWindow controller = fxmlLoader.getController();
            controller.setMariia(mariiaChatbot);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

