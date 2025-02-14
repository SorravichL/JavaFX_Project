package panel;

import config.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // Import Label
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class MenuPane extends BorderPane {
    private Button play, info; // Declare play, how, and info buttons
    private Image background = new Image(ClassLoader.getSystemResourceAsStream("image/backgrounds/menu.jpg"));
    private Image playPic = new Image(ClassLoader.getSystemResourceAsStream("image/buttons/play.png"));
    private Image infoPic = new Image(ClassLoader.getSystemResourceAsStream("image/buttons/info.png"));
    private Image gifImage = new Image(ClassLoader.getSystemResourceAsStream("image/buttons/slime.gif")); // Change "your_gif.gif" to your actual GIF file
    BackgroundImage backgroundImage = new BackgroundImage(background,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, new BackgroundSize(
            this.getWidth(), // Width of the MenuPane
            this.getHeight(), // Height of the MenuPane
            false, // Width is relative
            false, // Height is relative
            true, // Width is contain
            true // Height is contain
    ));

    public MenuPane() {
        super();
        this.setBackground(new Background(backgroundImage));
        // Add GIF image above the play button
        ImageView gifImageView = new ImageView(gifImage);
        gifImageView.setFitWidth(Config.tileSize);
        gifImageView.setFitHeight(Config.tileSize);
        setButton();

        createTitle();
        setInfoButton();
        VBox centerBox = setPlayButton(gifImageView);

        // Set padding to move the play button up
        VBox.setMargin(gifImageView, new Insets(-150, 0, 0, 0)); // Adjust the top margin as needed
        VBox.setMargin(play, new Insets(-13, 0, 0, 0)); // Adjust the top margin as needed

        // Set the center of the BorderPane to the VBox containing the GIF image and the play button
        this.setCenter(centerBox);

        setButtonHover();

        // Add action for play button
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Goto.startGame();
            }
        });
        info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Goto.infoScreen();
            }
        });
    }

    public void createTitle() {
        // Create title label
        Label titleLabel = new Label("Lost in Island");
        titleLabel.setStyle("-fx-font-size: 120px; -fx-text-fill: white; -fx-font-weight: bold;"); // Adjust font size, color, and style
        VBox.setMargin(titleLabel, new Insets(50, 0, 20, 0)); // Add padding to the top

        // Set the title label above the play button
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        this.setTop(titleBox);
    }

    public void setInfoButton() {
        // Set the info button in the bottom-left
        HBox bottomRightBox = new HBox(info);
        bottomRightBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRightBox.setSpacing(10); // Adjust spacing as needed
        HBox.setMargin(info, new Insets(0, 10, 10, 0)); // Add margin to the left
        this.setBottom(bottomRightBox);
    }

    public VBox setPlayButton(ImageView gifImageView) {
        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(gifImageView);
        centerBox.getChildren().add(play);
        return centerBox;
    }

    public void setButtonHover() {
        play.setOnMouseEntered(e -> play.setStyle("-fx-background-color: #666666;"));
        play.setOnMouseExited(e -> play.setStyle("-fx-background-color: black;"));

        info.setOnMouseEntered(e -> info.setStyle("-fx-background-color: #666666;"));
        info.setOnMouseExited(e -> info.setStyle("-fx-background-color: black;"));
    }

    public void setButton() {
        play = new Button();
        play.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        ImageView playImageView = new ImageView(playPic); // Create ImageView for playPic
        playImageView.setFitHeight(Config.tileSize * 2);
        playImageView.setFitWidth(Config.tileSize * 6);
        play.setGraphic(playImageView); // Set playPic as graphic for the play button

        info = new Button(); // Initialize the info button
        info.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        ImageView infoImageView = new ImageView(infoPic); // Create ImageView for infoPic
        infoImageView.setFitHeight(Config.tileSize * 2);
        infoImageView.setFitWidth(Config.tileSize * 2);
        infoImageView.setPreserveRatio(false);
        info.setGraphic(infoImageView); // Set infoPic as graphic for button

        info.setPrefWidth(Config.tileSize * 2);
        info.setPrefHeight(Config.tileSize * 2);
    }
}