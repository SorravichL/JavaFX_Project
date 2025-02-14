package panel;
import config.GameState;
import javafx.animation.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Main;

public class Goto {
    public static void startGame() {
        // Create a new Rectangle covering the entire scene
        Rectangle cover = new Rectangle(0, 0, RootPane.getInstance().getWidth(), RootPane.getInstance().getHeight());
        cover.setFill(Color.TRANSPARENT); // Start with transparent fill
        RootPane.getInstance().getChildren().add(cover);

        // Create the first FillTransition to turn the rectangle black
        FillTransition fillToBlack = new FillTransition(Duration.seconds(2), cover);
        fillToBlack.setFromValue(Color.TRANSPARENT);
        fillToBlack.setToValue(Color.BLACK);


        // Create the second FillTransition to turn the rectangle transparent again
        FillTransition fillToTransparent = new FillTransition(Duration.seconds(2), cover);
        fillToTransparent.setFromValue(Color.BLACK);
        fillToTransparent.setToValue(Color.TRANSPARENT);
        fillToTransparent.setDelay(Duration.seconds(1)); // Delay the second transition

        // Chain the two transitions together
        fillToBlack.setOnFinished(event -> {
            RootPane.getInstance().getChildren().remove(RootPane.getInstance().getChildren().size()-2);
            fillToTransparent.play();
        });

        fillToTransparent.setOnFinished(event -> {
            RootPane.getInstance().getChildren().removeLast();
        });

        // Play the first fill transition
        fillToBlack.play();
        GameState.isStart = true;
    }

    public static void infoScreen() {
        InfoPane infoPane = new InfoPane();
        RootPane.getInstance().getChildren().add(infoPane);
        // Adjust alignment as needed
    }

    public static void closeTab() {
        RootPane.getInstance().getChildren().removeLast();
    }
}
