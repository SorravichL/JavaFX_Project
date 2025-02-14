package object;

import interfacep.Sellable;
import object.items.Item;

public abstract class Thing extends Item implements Sellable {
    protected int price;

    @Override
    public int getPrice() {
        return price;
    }
}
