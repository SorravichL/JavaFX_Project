package object.items;

import javafx.scene.image.Image;

public class NormalHammer extends BaseHammer {
    public NormalHammer() {
        super();
        setRepairHeath(20);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/objects/Hammer.png"));
        name = "Repairing Hammer";
        description = "["+name+"]\nuse for increase house HP\n *Can't be more than max HP";
    }

    @Override
    public int getPrice() {
        return 20;
    }
}
