package object.weapon;

import javafx.scene.image.Image;

public class IronSword extends BaseWeapon{
    public IronSword() {
        setAttackValue(5);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/weapon/iron_sword.png"));
        price=30;
        name = "Iron Sword";
        description = "["+name+"]\nObviously some kind\nof normal sword.";
    }
}
