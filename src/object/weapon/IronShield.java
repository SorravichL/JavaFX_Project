package object.weapon;

import javafx.scene.image.Image;

public class IronShield extends BaseShield{
    public IronShield() {
        setDefenseValue(10);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/shield/iron_shield.png"));
        price = 30;
        name = "Iron Shield";
        description = "["+name+"]\nObviously some kind\nof normal shield.";
    }
}
