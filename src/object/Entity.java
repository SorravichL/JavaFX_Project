package object;

import javafx.scene.shape.Rectangle;

public abstract class Entity extends OBJ {
    protected int speed, sideSpeed;
    private int strength, attack, defense;
    protected Rectangle solidArea;
    protected Rectangle attackArea;

    protected int solidAreaDefaultX,solidAreaDefaultY;
    protected boolean isCollisionOn=false;
    protected String direction;
    protected boolean isInvincible = false;
    protected boolean isHpBarOn = false;
    protected boolean isAlive = true;
    private boolean isDying = false;
    protected int invincibleCounter = 0;
    protected int hpBarCounter = 0;
    protected int dyingCounter = 0;
    private int life;
    private int maxLife;

    //Constructor
    public Entity() {
        setVisible(true);
        setDestroyed(false);
    }

   //Getter and Setter

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public boolean isCollisionOn() {
        return isCollisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.isCollisionOn = collisionOn;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setSideSpeed(int speed) {
        int sideSpeed =(int) (speed * (Math.cos(Math.toRadians(45.0))));
        if (sideSpeed==0) {
            sideSpeed=1;
        }
        this.sideSpeed=sideSpeed;
    }


    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        this.isInvincible = invincible;
    }

    public void update() {}

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
        this.maxLife = maxLife;
    }

    public void setHpBarOn(boolean hpBarOn) {
        this.isHpBarOn = hpBarOn;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        this.isDying = dying;
    }

    public int getDyingCounter() {
        return dyingCounter;
    }

    public void setDyingCounter(int dyingCounter) {
        this.dyingCounter = dyingCounter;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
