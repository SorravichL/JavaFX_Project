package object.weapon;

import javafx.scene.image.Image;

public class PowerfulShield extends BaseShield{
    public PowerfulShield() {
        setDefenseValue(15);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/shield/powerful_shield.png"));
        price = 50;
        name = "Powerful Shield";
        description = "["+name+"]\nMaybe better than\nof normal shield.";
    }
}