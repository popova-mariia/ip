package mariiachatbot;//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import mariiachatbot.MariiaChatbot;
//
//
//public class mariiachatbot.Main extends Application {
//
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));
//    private MariiaChatbot mariiaChatbot = new MariiaChatbot("./src/main/java/data/mariia.txt");
//
//    @Override
//    public void start(Stage stage) {
//        //Setting up required components
//
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        mariiachatbot.ui.DialogBox dialogBox = new mariiachatbot.ui.DialogBox("Hello!", userImage);
//        dialogContainer.getChildren().addAll(dialogBox);
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }
//
//    private void handleUserInput() {
//        String userText = userInput.getText();
//        String dukeText = mariiaChatbot.getResponse(userInput.getText());
//        dialogContainer.getChildren().addAll(
//                mariiachatbot.ui.DialogBox.getUserDialog(userText, userImage),
//                mariiachatbot.ui.DialogBox.getDukeDialog(dukeText, dukeImage)
//        );
//        userInput.clear();
//    }
//}


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

    private MariiaChatbot bot = new MariiaChatbot("./src/main/java/data/mariia.txt");

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            System.out.println("fxmlLoader is: " + fxmlLoader);
            AnchorPane ap = fxmlLoader.load();
            MainWindow controller = fxmlLoader.getController();
            controller.setMariia(bot);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

