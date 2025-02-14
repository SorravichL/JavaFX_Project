package object.monster;

import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class YellowSlime extends Slime {
    private Image slime_jump_1 = new Image(ClassLoader.getSystemResourceAsStream("image/slime/Yelllow/slime_jumping_1.png"));
    private Image slime_jump_2 = new Image(ClassLoader.getSystemResourceAsStream("image/slime/Yelllow/slime_jumping_2.png"));
    private Image slime_jump_3 = new Image(ClassLoader.getSystemResourceAsStream("image/slime/Yelllow/slime_jumping_3.png"));

    public YellowSlime(int x, int y) {
        super(x, y);
        setDef(slime_jump_1);
    }

    @Override
    public void setStatus() {
        //status
        setMaxLife(4);
        setLife(getMaxLife());
        setStrength(1);
        setAttack(2);
        setDefense(1);
    }

    @Override
    public void setAction() {
        if (getSpriteCounter() > 40) {
            setSpriteCounter(0);
        } else {
            if (getSpriteCounter() <= 10) {
                setSpriteNum(1);
            } else if (getSpriteCounter() > 10 && getSpriteCounter() <= 20) {
                setSpriteNum(2);
            } else if (getSpriteCounter() > 20 && getSpriteCounter() <= 30) {
                setSpriteNum(3);
            } else if (getSpriteCounter() > 30) {
                setSpriteNum(4);
            }
        }
        setPicture();
    }

    private void setPicture() {
        if (getSpriteNum() == 1) {
            setDef(slime_jump_1);
        }
        if (getSpriteNum() == 2) {
            setDef(slime_jump_2);
        }
        if (getSpriteNum() == 3) {
            setDef(slime_jump_3);
        }
        if (getSpriteNum() == 4) {
            setDef(slime_jump_2);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        int screenX = worldX - getPlayer().getWorldX() + getPlayer().getScreenX();
        int screenY = worldY - getPlayer().getWorldY() + getPlayer().getScreenY();

        if (worldX + Config.tileSize > getPlayer().getWorldX() - getPlayer().getScreenX() &&
                worldX - Config.tileSize < getPlayer().getWorldX() + getPlayer().getScreenX() &&
                worldY + Config.tileSize > getPlayer().getWorldY() - getPlayer().getScreenY() &&
                worldY - Config.tileSize < getPlayer().getWorldY() + getPlayer().getScreenY()) {
            gc.drawImage(getDef(), screenX, screenY, Config.tileSize, Config.tileSize);
        }

        if (isHpBarOn) {
            drawHPBar(gc, screenX, screenY);

            hpBarCounter++;
            if (hpBarCounter > 600) {
                hpBarCounter = 0;
                isHpBarOn = false;
            }
        }
    }

    @Override
    public void drawDead(GraphicsContext gc) {
        setDyingCounter(getDyingCounter() + 1);

        if (getDyingCounter()==1) {
            playSlimeSound();
        }

        if (getDyingCounter() <= 10) {
            setDef(new Image(ClassLoader.getSystemResourceAsStream("image/slime/Yelllow/slime_death1.png")));
        } else if (getDyingCounter() > 10 && getDyingCounter() <= 20) {
            setDef(new Image(ClassLoader.getSystemResourceAsStream("image/slime/Yelllow/slime_death2.png")));
        } else if (getDyingCounter() > 20 && getDyingCounter() <= 30) {
            setDef(new Image(ClassLoader.getSystemResourceAsStream("image/slime/Yelllow/slime_death3.png")));
        } else if (getDyingCounter() > 30) {
            setDying(false);
            setAlive(false);
        }
    }
}
