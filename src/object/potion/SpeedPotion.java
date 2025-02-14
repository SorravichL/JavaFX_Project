package object.potion;

import config.Status;
import javafx.scene.image.Image;
import object.Player;

public class SpeedPotion extends Potion{
    public SpeedPotion() {
        super();
        z=2;
        image = new Image(ClassLoader.getSystemResourceAsStream("image/items/green_potion.png"));
        name ="Speed Potion";
        price=5;
        description="["+name+"]\nFor increase speed";
        isCollision=false;
    }

    @Override
    public void use( Player p) {
        Status.speedBuff=true;
    }
}
