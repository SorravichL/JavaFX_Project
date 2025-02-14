package object.weapon;

import javafx.scene.image.Image;

public class LegendarySword extends BaseWeapon{
    public LegendarySword() {
        setAttackValue(15);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/weapon/legendary_sword.png"));
        price=100;
        name = "Legendary Sword";
        description = "["+name+"]\nThe most efficient\nsword in this game.";
    }

}
