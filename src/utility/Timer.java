package utility;

import config.Config;
import config.GameState;
import config.Status;
import javafx.animation.FillTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;
import panel.RootPane;

public class Timer {
    public void drawTime(double elapsedTime, GraphicsContext gc) {
        drawFrame(gc);
        // Start the clock from 06:00 instead of 00:00
        long startingTime = 6 * 3600; // 6 hours * 3600 seconds/hour
        long adjustedTime = ((long) elapsedTime + startingTime) % (24 * 3600); // Ensure time wraps around at 24:00
        gc.setFont(new Font("Georgia", 30));
        gc.setFill(Color.WHITE); // Set the color to white for clear visibility
        String formattedTime = formatTime(adjustedTime);
        gc.fillText(formattedTime, Config.tileSize * 30-10, Config.tileSize);
        if (formattedTime.equals("17:50")||formattedTime.equals("05:50")) {
            changePhase(formattedTime);
        }
        // Check if the adjusted time is 18:00
        if (formattedTime.equals("18:00")) {
            GameState.isNightState = true;
        }
        if (formattedTime.equals("06:00")) {
            GameState.isNightState = false;
        }

        // Check if the adjusted time is 00:00 and day hasn't been incremented yet
        if (formattedTime.equals("00:00") && !GameState.isDayIncremented) {
            Config.day++; // Increment the day
            Status.dexBuff=false;
            Status.strengthBuff=false;
            Status.speedBuff=false;
            GameState.isDayIncremented = true; // Mark day as incremented
        }

        // Reset the dayIncremented flag if the adjusted time is not 00:00
        if (!formattedTime.equals("00:00")) {
            GameState.isDayIncremented = false;
        }

        gc.fillText("Day : "+Config.day, Config.tileSize * 27, Config.tileSize);
        gc.setFont(new Font("Georgia", 20));
    }
    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        return String.format("%02d:%02d", hours, minutes);
    }
    private void drawFrame(GraphicsContext gc) {
        UserInterface.drawSubWindow(Config.tileSize*25-10,14,Config.tileSize*7,Config.tileSize,gc);
        gc.drawImage(GameState.dayPic,Config.tileSize*25+5,22,Config.tileSize*0.7,Config.tileSize*0.7);
    }
    public static void changePhase(String formattedTime) {
        // Create a new Rectangle covering the entire scene
        Rectangle cover = new Rectangle(0, 0, RootPane.getInstance().getWidth(), RootPane.getInstance().getHeight());
        cover.setFill(Color.TRANSPARENT); // Start with transparent fill
        RootPane.getInstance().getChildren().add(cover);

        // Create the first FillTransition to turn the rectangle black
        FillTransition fillToBlack = new FillTransition(Duration.seconds(1), cover);
        fillToBlack.setFromValue(Color.TRANSPARENT);
        fillToBlack.setToValue(Color.BLACK);


        // Create the second FillTransition to turn the rectangle transparent again
        FillTransition fillToTransparent = new FillTransition(Duration.seconds(1), cover);
        fillToTransparent.setFromValue(Color.BLACK);
        fillToTransparent.setToValue(Color.TRANSPARENT);
        fillToTransparent.setDelay(Duration.seconds(0.5)); // Delay the second transition

        // Chain the two transitions together
        fillToBlack.setOnFinished(event -> {
            fillToTransparent.play();
        });

        fillToTransparent.setOnFinished(event -> {
            RootPane.getInstance().getChildren().removeLast();
        });

        // Play the first fill transition
        if (formattedTime.equals("17:50")||formattedTime.equals("05:50")) {
            fillToBlack.play();
        }
    }
}
