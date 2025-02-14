package object.items;

import interfacep.Sellable;

public abstract class BaseHammer extends Item implements Sellable {
    protected int repairHealth;
    public BaseHammer() {
        super();
    }

    public void use() {
        House.getInstance().setLife(House.getInstance().getLife()+getRepairHeath());
    }

    public int getRepairHeath() {
        return repairHealth;
    }

    public void setRepairHeath(int repairHeath) {
        this.repairHealth = repairHeath;
    }

}
