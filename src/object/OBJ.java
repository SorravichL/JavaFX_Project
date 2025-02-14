package object;

import panel.GamePanel;
import render.Renderable;

public abstract class OBJ implements Renderable {
    protected int worldX,worldY;
    protected int z;
    protected boolean isDestroyed;//default is false
    protected boolean isVisible;
    protected GamePanel gp;
    @Override
    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.isDestroyed = destroyed;
    }
    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
}
