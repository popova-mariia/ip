package johnny.ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import johnny.Johnny;
import johnny.command.Command;
import johnny.parser.Parser;


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

    private Johnny johnny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_avatar.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot_avatar.png"));

    /**
     * Initializes the main window by binding the scroll pane's vertical value to the dialog container's height.
     */
    @FXML
    public void initialize() {
        dialogContainer.setStyle("-fx-background-color: #efe901;");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        System.out.println("johnny.ui.MainWindow initialize() called!");
    }

    /** Injects the Duke instance */
    public void setJohnny(Johnny johnny) {
        assert johnny != null : "Bot instance cannot be null";
        this.johnny = johnny;
        String welcomeMessage = johnny.welcomeUser();
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(welcomeMessage, botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = Parser.parseCommand(input);
        String response = johnny.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
        if (command.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }
}
