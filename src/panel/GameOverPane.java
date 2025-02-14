package panel;

import config.GameState;
import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameOverPane {
     public static void gameOver() {
         // Create a new Rectangle covering the entire scene
         Rectangle cover = new Rectangle(0, 0, RootPane.getInstance().getWidth(), RootPane.getInstance().getHeight());
         cover.setFill(Color.BLACK); // Start with transparent fill
         RootPane.getInstance().getChildren().add(cover);

         // Create the first FillTransition to turn the rectangle black
         FillTransition fillToBlack = new FillTransition(Duration.seconds(2), cover);
         fillToBlack.setFromValue(Color.TRANSPARENT);
         fillToBlack.setToValue(Color.BLACK);

         fillToBlack.play();

         // Create title label
         Label titleLabel = new Label("- Game Over -");
         titleLabel.setStyle("-fx-font-size: 100px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: Arial;"); // Adjust font size, color, and style
         RootPane.getInstance().getChildren().add(titleLabel);
     }
}
