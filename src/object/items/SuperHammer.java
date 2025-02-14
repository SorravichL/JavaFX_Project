package object.items;

import javafx.scene.image.Image;

public class SuperHammer extends BaseHammer {
    public SuperHammer() {
        super();
        setRepairHeath(House.getInstance().getMaxLife());
        image = new Image(ClassLoader.getSystemResourceAsStream("image/objects/SuperHammer.png"));
        name = "Super Repairing Hammer";
        description = "["+name+"]\nuse for increase house HP\nto the max HP value";
    }

    @Override
    public int getPrice() {
        return 100;
    }
}
