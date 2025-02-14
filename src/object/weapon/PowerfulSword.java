package object.weapon;

import javafx.scene.image.Image;

public class PowerfulSword extends BaseWeapon{
    public PowerfulSword() {
        setAttackValue(10);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/weapon/powerful_sword.png"));
        price=50;
        name = "Powerful Sword";
        description = "["+name+"]\nMaybe better than\n normal sword.";
    }
}