package object.weapon;

import javafx.scene.image.Image;

public class NewbieSword extends BaseWeapon {
    public NewbieSword() {
        setAttackValue(1);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/weapon/newbie_sword.png"));
        price=10;
        name = "Newbie Sword";
        description = "["+name+"]\nSilly ass sword.";
    }
}
