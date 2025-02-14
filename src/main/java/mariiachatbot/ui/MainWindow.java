package mariiachatbot.ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mariiachatbot.MariiaChatbot;
import mariiachatbot.command.Command;
import mariiachatbot.parser.Parser;


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

    private MariiaChatbot mariiaChatbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));

    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        System.out.println("mariiachatbot.ui.MainWindow initialize() called!");
    }

    /** Injects the Duke instance */
    public void setMariia(MariiaChatbot mariiaChatbot) {
        System.out.println("setMariia(...) called with: " + mariiaChatbot);
        this.mariiaChatbot = mariiaChatbot;
        String welcomeMessage = mariiaChatbot.welcomeUser();
        // Alternatively, hardcode it:
        // String welcomeMessage = "***\n Hello! I'm MariiaChatbot\n What can I do for you?\n***";
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
        String response = mariiaChatbot.getResponse(input);
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
