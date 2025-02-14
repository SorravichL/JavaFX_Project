package panel;

import config.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Close extends Button {
    private Image closePic = new Image(ClassLoader.getSystemResourceAsStream("image/buttons/close.png"));
    public Close() {
        super();
        setPrefSize(25,25);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        ImageView closeImageView = new ImageView(closePic); // Create ImageView for playPic
        closeImageView.setFitHeight(25);
        closeImageView.setFitWidth(25);
        this.setGraphic(closeImageView);
        this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: #666666;"));
        this.setOnMouseExited(e -> this.setStyle("-fx-background-color: black;"));
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Goto.closeTab();
            }
        });
    }
}