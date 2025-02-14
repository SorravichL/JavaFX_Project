package main;

import config.Config;
import config.GameState;
import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import object.monster.Slime;
import panel.GamePanel;
import panel.MenuPane;
import panel.RootPane;
import render.RenderableHolder;
import utility.InputUtility;
import utility.Timer;
import utility.UserInterface;

import java.net.URL;
import java.util.TimerTask;

public class Main extends Application {
    private final double TIME_DILATION_FACTOR = 240.0;
    private long startTime;
    private boolean firstTime = false;
    public static MediaPlayer songPlayer,swordPlayer,slimeDeadPlayer;

    public static URL menuMusic,gameMusic;

    @Override
    public void start(Stage stage) throws Exception {
        startTime = System.nanoTime();
        //setup pane
        RootPane rootpane = RootPane.getInstance();
        MenuPane menuPane = new MenuPane();
        GamePanel gamepanel = GamePanel.getInstance();
        GraphicsContext gc = gamepanel.getGraphicsContext2D();
        GameLogic logic = new GameLogic();
        RenderableHolder renderableHolder = RenderableHolder.getInstance();
        Timer timer = new Timer();
        rootpane.getChildren().add(gamepanel);
        rootpane.getChildren().add(menuPane);
        gamepanel.setFocusTraversable(true);

        //setup scene
        Scene scene = new Scene(rootpane, Config.screenWidth, Config.screenHeight);
        //setup stage
        stage.setScene(scene);
        stage.setTitle("Villager");
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5); // Darken the canvas by reducing brightness

        menuMusic = getClass().getResource("sounds/bohemian.mp3");
        gameMusic = getClass().getResource("sounds/bgMusic.mp3");
        songPlayer = new MediaPlayer(new Media(menuMusic.toString()));
        songPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                songPlayer.seek(Duration.ZERO);
            }
        });
        songPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                songPlayer.play();
            }
        });

        // Apply the ColorAdjust effect to the canvas

        //animation timer for drawing
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (GameState.isStart) {
                    if(!firstTime) {
                        fadeOutSound();
                        firstTime=true;
                    }
                    double elapsedTime = (now - startTime) / 1_000_000_000.0;
                    elapsedTime *= TIME_DILATION_FACTOR;
                    gamepanel.paintComponent();
                    logic.logicUpdate();
                    renderableHolder.update();
                    timer.drawTime(elapsedTime, gc);
                    InputUtility.getKeyPressed().remove(KeyCode.ENTER);
                    InputUtility.getKeyPressed().remove(KeyCode.SPACE);
                }
            }
        };
        animation.start();//start animation timer
    }
    private static void fadeOutSound() {
        // Gradually decrease volume to 0 over 3 seconds
        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(songPlayer.volumeProperty(), songPlayer.getVolume())),
                new KeyFrame(Duration.seconds(3), new KeyValue(songPlayer.volumeProperty(), 0)));
        fadeOut.setOnFinished(event -> {
            songPlayer.stop(); // Stop the current media player
            songPlayer = new MediaPlayer(new Media(gameMusic.toString())); // Create a new media player with gameMusic URL
            songPlayer.setOnEndOfMedia(() -> songPlayer.seek(Duration.ZERO));
            songPlayer.setOnReady(() -> songPlayer.play());
            fadeInSound();
        });
        fadeOut.play();
    }
    private static void fadeInSound() {
        // Gradually increase volume from 0 to original volume over 3 seconds
        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(songPlayer.volumeProperty(), 0)),
                new KeyFrame(Duration.seconds(4), new KeyValue(songPlayer.volumeProperty(), 1)));
        fadeIn.play();
    }
}
