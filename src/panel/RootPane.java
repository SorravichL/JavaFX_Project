package panel;

import config.Config;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import object.Player;
import utility.InputUtility;

public class RootPane extends StackPane {
    public static RootPane instance = new RootPane();

    public static RootPane getInstance() {
        return instance;
    }
    public RootPane() {
        this.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN,null,null)));
    }
}