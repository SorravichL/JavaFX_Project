package object.weapon;

import javafx.scene.image.Image;

public class LegendaryShield extends BaseShield{
    public LegendaryShield() {
        setDefenseValue(20);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/shield/legendary_shield.png"));
        price = 100;
        name = "Powerful Shield";
        description = "["+name+"]\nThe most efficient\nshield in this game.";
    }
}
