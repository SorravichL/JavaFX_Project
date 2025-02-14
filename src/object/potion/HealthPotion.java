package object.potion;

import config.Config;
import javafx.scene.image.Image;
import object.Player;
import object.Thing;
import object.weapon.BaseShield;

public class HealthPotion extends Potion {
    public HealthPotion() {
        super();
        z=2;
        image = new Image(ClassLoader.getSystemResourceAsStream("image/items/red_potion.png"));
        name ="Health Potion";
        price=5;
        description="["+name+"]\nFor healing";
        isCollision=false;
    }

    @Override
    public void use( Player p) {
        p.setLife(p.getLife()+1);
    }
}
