package object.weapon;

import javafx.scene.image.Image;
import object.Thing;

public abstract class BaseShield extends Thing {
    protected Image image;
    protected int defenseValue=0;
    protected int price = 0;

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }
    public Image getImage() {
        return image;
    }
    @Override
    public int getPrice() {
        return price;
    }
}
