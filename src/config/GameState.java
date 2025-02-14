package config;
import javafx.scene.image.Image;

public class GameState {
    public static boolean isChestState,isTraderState,isChooseState;
    public static boolean isStart = false;
    public static boolean isNormalState;
    public static boolean isNightState = false;
    public static boolean isDayIncremented = false;
    public static void update() {
        isNormalState= !isChestState && !isTraderState;
        if (!isNightState) {
            dayPic = Config.sunPic;
        } else {
            dayPic = Config.moonPic;
        }
    }
    public static int traderPage=1;
    public static Image dayPic;
    //0=default play state
    //1=not rendering ui state
}
