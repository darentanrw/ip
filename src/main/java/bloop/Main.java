package bloop;

import java.io.IOException;

import bloop.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bloop using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    private final BloopChatService bloop = new BloopChatService(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Bloop");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setBloopChatService(bloop);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
