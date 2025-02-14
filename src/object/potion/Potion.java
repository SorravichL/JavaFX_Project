package object.potion;

import object.Player;
import object.Thing;

public abstract class Potion extends Thing {
    public abstract void use( Player p);
}
