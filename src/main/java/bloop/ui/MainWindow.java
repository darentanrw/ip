package bloop.ui;

import java.io.InputStream;

import bloop.BloopChatService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BloopChatService bloop;

    private final Image userImage = loadAvatar("/images/user.jpg", Color.CORNFLOWERBLUE);
    private final Image bloopImage = loadAvatar("/images/bloop.jpg", Color.SALMON);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the backend chat service and shows an initial greeting.
     */
    public void setBloopChatService(BloopChatService bloop) {
        this.bloop = bloop;
        dialogContainer.getChildren().add(DialogBox.getBloopDialog(bloop.getWelcomeMessage(), bloopImage));
    }

    /**
     * Handles both send-button clicks and Enter key submissions.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        String response = bloop.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBloopDialog(response, bloopImage));
        userInput.clear();

        if (bloop.shouldExit(input)) {
            Platform.runLater(Platform::exit);
        }
    }

    private Image createAvatar(Color color) {
        int size = 80;
        WritableImage image = new WritableImage(size, size);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                image.getPixelWriter().setColor(x, y, color);
            }
        }
        return image;
    }

    private Image loadAvatar(String resourcePath, Color fallbackColor) {
        try (InputStream stream = getClass().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                return createAvatar(fallbackColor);
            }
            return new Image(stream);
        } catch (Exception e) {
            return createAvatar(fallbackColor);
        }
    }
}
