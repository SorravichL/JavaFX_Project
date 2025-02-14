package object.items;

import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import object.Player;

public class House extends Item {
    private int life, maxLife;
    protected boolean isInvincible = false;
    protected int invincibleCounter = 0;

    public static House instance = new House();

    public static House getInstance() {
        return instance;
    }

    public House() {
        super();
        z = 10;
        name = "House";
        solidArea = new Rectangle(0, 0, Config.tileSize*10, Config.tileSize*10);
        setWorldX(40 * Config.tileSize);
        setWorldY(40 * Config.tileSize);
        setMaxLife(100);
        setLife(100);
        setCollision(true);
    }

    @Override
    public void draw(GraphicsContext gc) {
        int screenX = worldX - player.getWorldX() + player.getScreenX();
        int screenY = worldY - player.getWorldY() + player.getScreenY();

        if (worldX + Config.tileSize > player.getWorldX() - player.getScreenX() &&
                worldX - Config.tileSize < player.getWorldX() + player.getScreenX() &&
                worldY + Config.tileSize > player.getWorldY() - player.getScreenY() &&
                worldY - Config.tileSize < player.getWorldY() + player.getScreenY()) {
            gc.drawImage(image, screenX, screenY, Config.tileSize*5, Config.tileSize*5);
        }

        drawHPBar(gc, screenX, screenY);
    }

    public void update() {
        if (isInvincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                isInvincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void drawHPBar (GraphicsContext gc, int screenX, int screenY) {
        double oneScale = (double)(Config.tileSize*10)/getMaxLife();
        double hpBarValue = oneScale*getLife();

        gc.setFill(Color.BLACK);
        gc.fillRect(screenX-1, screenY - 30, (Config.tileSize * 10) + 2, 12);

        gc.setFill(Color.RED);
        gc.fillRect(screenX, screenY - 29, hpBarValue, 10);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = Math.min(life, getMaxLife());
        this.life = Math.max(getLife(), 0);
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = Math.max(maxLife, 0);
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        this.isInvincible = invincible;
    }

}
