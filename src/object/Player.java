package object;

import config.Status;
import interfacep.Storable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logic.GameLogic;
import object.items.Item;
import object.monster.Slime;
import object.weapon.*;
import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import panel.GamePanel;
import utility.InputUtility;

import java.net.URL;
import java.util.ArrayList;


import static main.Main.swordPlayer;
import static utility.LoadUtility.*;

public class Player extends Entity implements Storable {
    public final int screenX;
    public final int screenY;
    //counter
    private int spriteCounter = 0;
    private int attackCounter = 0;
    private int spriteNum = 1;
    private int thirtyFiveCounter = 0;

    private boolean isAttack = false;

    private URL swordURL;


    //Character Attributes
    private int strength,buffStrength, level, dex,buffDex,buffSpeed,defaultDex,defaultSpeed,defaultStrength, attack, defense, exp, nextLevelExp, money;
    private BaseWeapon currentWeapon = new NewbieSword();
    private BaseShield currentShield = new NewbieShield();
    private ArrayList<Item> inventory;
    public static Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }

    private GamePanel gp = GamePanel.getInstance();

    private Image def;//display image at that moment
    private Image attackDef;

    public Player() {
        super();
        //where to spawn
        worldX = Config.tileSize * (15 + Config.fixedPosition);
        worldY = Config.tileSize * (15 + Config.fixedPosition);
        z = 2;
        //where to draw
        screenX = Config.screenWidth / 2 - (Config.tileSize) / 2;
        screenY = Config.screenHeight / 2 - (Config.tileSize) / 2;
        direction = "down";
        def = down;
        playerLoad();
        setStatus();
        setRectangle();
        setItems();
        setSound();
    }

    private void setSound() {
        swordURL = getClass().getResource("sounds/sword.mp3");
    }

    private void setItems() {
        inventory = new ArrayList<>(20);
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }

    private void setRectangle() {
        solidArea = new Rectangle();
        solidArea.setX(8);
        solidArea.setY(16);
        solidArea.setWidth(32);
        solidArea.setHeight(32);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();

        attackArea = new Rectangle(Config.tileSize,Config.tileSize);
    }

    private void setStatus() {
        //status
        setLevel(1);
        setDefaultSpeed(5);
        setSpeed(getDefaultSpeed());
        setSideSpeed(getSpeed());
        setBuffSpeed(getDefaultSpeed()*2);
        setMaxLife(8);
        setLife(8);
        setDefaultStrength(1);
        setStrength(getDefaultStrength());
        setDefaultDex(1);
        setDex(getDefaultDex());
        setExp(0);
        setNextLevelExp(5);
        setMoney(100);
        setAttack(getAttack());
        setDefense(getDefense());
        setBuffDex(getDex()+1);
        setBuffSpeed(getSpeed()+1);
        setBuffStrength(getStrength()+1);
    }
    private void up() {
        if (spriteNum == 1) {
            def = up;
        }
        if (spriteNum == 2) {
            def = up2;
        }
        if (spriteNum == 3) {
            def = up3;
        }
        direction = "up";
        if (!isCollisionOn()) {
            worldY -= speed;
        }
    }

    private void down() {
        if (spriteNum == 1) {
            def = down;
        }
        if (spriteNum == 2) {
            def = down2;
        }
        if (spriteNum == 3) {
            def = down3;
        }
        direction = "down";
        if (!isCollisionOn()) {
            worldY += speed;
        }
    }

    private void left() {
        if (spriteNum == 1) {
            def = left;
        }
        if (spriteNum == 2) {
            def = left2;
        }
        if (spriteNum == 3) {
            def = left3;
        }
        direction = "left";
        if (!isCollisionOn()) {
            worldX -= speed;
        }
    }

    private void right() {
        if (spriteNum == 1) {
            def = right;
        }
        if (spriteNum == 2) {
            def = right2;
        }
        if (spriteNum == 3) {
            def = right3;
        }
        direction = "right";
        if (!isCollisionOn()) {
            worldX += speed;
        }
    }

    private void upleft() {
        if (spriteNum == 1) {
            def = upleft;
        }
        if (spriteNum == 2) {
            def = upleft2;
        }
        if (spriteNum == 3) {
            def = upleft3;
        }
        direction = "upleft";
        if (!isCollisionOn()) {
            worldX -= sideSpeed;
            worldY -= sideSpeed;
        }
    }

    private void upright() {
        if (spriteNum == 1) {
            def = upright;
        }
        if (spriteNum == 2) {
            def = upright2;
        }
        if (spriteNum == 3) {
            def = upright3;
        }
        direction = "upright";
        if (!isCollisionOn()) {
            worldX += sideSpeed;
            worldY -= sideSpeed;
        }
    }

    private void downright() {
        if (spriteNum == 1) {
            def = downright;
        }
        if (spriteNum == 2) {
            def = downright2;
        }
        if (spriteNum == 3) {
            def = downright3;
        }
        direction = "downright";
        if (!isCollisionOn()) {
            worldX += sideSpeed;
            worldY += sideSpeed;
        }
    }

    private void downleft() {
        if (spriteNum == 1) {
            def = downleft;
        }
        if (spriteNum == 2) {
            def = downleft2;
        }
        if (spriteNum == 3) {
            def = downleft3;
        }
        direction = "downleft";
        if (!isCollisionOn()) {
            worldX -= sideSpeed;
            worldY += sideSpeed;
        }
    }
    public void statusCheck() {
        if (Status.speedBuff) {
            setSpeed(buffSpeed);
            setSideSpeed(getBuffSpeed());
        }else {
            setSpeed(defaultSpeed);
            setSideSpeed(getDefaultSpeed());
        }
        if (Status.strengthBuff) {
            setStrength(getBuffStrength());
        }else {
            setStrength(getDefaultStrength());
        }
        if (Status.dexBuff) {
            setDex(getBuffDex());
        }else {
            setDex(getDefaultDex());
        }

    }


    //fetch position
    public void update() {
        statusCheck();
        if (InputUtility.MouseInputUtility.isMouseClicked()) {
            isAttack = true;
        }
        attackCheck();
        setCollisionOn(false);
        gp.collisionChecker.checkTile(this);

        //if collision is false,player can move

        if (isInvincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                isInvincible = false;
                invincibleCounter = 0;
            }
        }
        spriteCount();
    }

    private void attackCheck() {
        if (isAttack) {
            attackingCount();
            thirtyFiveCounter++;
            if (thirtyFiveCounter==1) {
                playSwordSound();
                findMonsterIndex();
            }
            if (thirtyFiveCounter == 46) {
                thirtyFiveCounter = 0;
                isAttack = false;
            }
        }
        if (!isAttack) {
            move();
        }
    }

    private void playSwordSound() {
        swordPlayer = new MediaPlayer(new Media(swordURL.toString()));
        swordPlayer.setVolume(0.5);
        swordPlayer.play();
    }

    private void move() {
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.A)) {
            upleft();
        } else if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.D)) {
            upright();
        } else if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.D)) {
            downright();
        } else if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.A)) {
            downleft();
        } else if (InputUtility.isKeyPressed(KeyCode.W)) {
            up();
        } else if (InputUtility.isKeyPressed(KeyCode.S)) {
            down();
        } else if (InputUtility.isKeyPressed(KeyCode.A)) {
            left();
        } else if (InputUtility.isKeyPressed(KeyCode.D)) {
            right();
        }
    }

    //sprite counter so my player can walk
    private void spriteCount() {
        spriteCounter++;

        if (spriteCounter > 45) {
            spriteCounter = 0;
        } else {
            if (spriteCounter < 15) {
                spriteNum = 1;
            } else if (spriteCounter > 15 && spriteCounter <= 30) {
                spriteNum = 2;
            } else if (spriteCounter > 30) {
                spriteNum = 3;
            }
        }
    }

    public void attackingCount() {
        attackCounter++;
        firstAttackCounter();
        secondAttackCounter();
        thirdAttackCounter();
        resetAttackCounter();
    }

    public void firstAttackCounter() {
        if (attackCounter <= 10) {
            if (direction == "left" || direction == "downleft") {
                attackDef = sworddownleft1;
            }
            if (direction == "right" || direction == "upright") {
                attackDef = sworddownright1;
            }
            if (direction == "up" || direction == "upleft") {
                attackDef = swordupright1;
            }
            if (direction == "down" || direction == "downright") {
                attackDef = sworddownleft1;
            }
        }
    }

    public void secondAttackCounter() {
        if (attackCounter > 10 && attackCounter <= 35) {
            if (direction == "left" || direction == "downleft") {
                attackDef = sworddownleft2;
            }
            if (direction == "right" || direction == "upright") {
                attackDef = sworddownright2;
            }
            if (direction == "up" || direction == "upleft") {
                attackDef = swordupright2;
            }
            if (direction == "down" || direction == "downright") {
                attackDef = sworddownleft2;
            }
        }
    }

    public void thirdAttackCounter() {
        if (attackCounter > 35 && attackCounter <= 45) {
            if (direction == "left" || direction == "downleft") {
                attackDef = sworddownleft3;
            }
            if (direction == "right" || direction == "upright") {
                attackDef = sworddownright3;
            }
            if (direction == "up" || direction == "upleft") {
                attackDef = swordupright3;
            }
            if (direction == "down" || direction == "downright") {
                attackDef = sworddownleft3;
            }
        }
    }

    public void resetAttackCounter() {
        if (attackCounter > 45) {
            attackCounter = 0;
            attackDef = null;
        }
    }

    public void findMonsterIndex() {
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = (int) solidArea.getWidth();
        int solidAreaHeight = (int) solidArea.getHeight();

        switch (direction) {
            case "up", "upleft": worldY-= (int) attackArea.getHeight()+30;break;
            case "down", "downright": worldY+= (int) attackArea.getHeight();break;
            case "left", "downleft": worldX-= (int) attackArea.getWidth()+30;break;
            case "right", "upright": worldX+= (int) attackArea.getWidth();break;
        }

        ArrayList<Integer> monsterIndex = gp.getCollisionChecker().checkSlime(this,GameLogic.slimeList);
        damageMonster(monsterIndex);

        worldX=currentWorldX;
        worldY=currentWorldY;
        solidArea.setWidth(solidAreaWidth);
        solidArea.setHeight(solidAreaHeight);
    }

    public void damageMonster(ArrayList<Integer> i) {
        if (!i.isEmpty()) {
            System.out.println("HIT!");
            for (int e : i) {
                knockBackSlime(GameLogic.slimeList.get(e));
                GameLogic.slimeList.get(e).setLife((GameLogic.slimeList.get(e).getLife()-getAttack()));
                GameLogic.slimeList.get(e).setHpBarOn(true);
            }
        } else {
            System.out.println("MISS!");
        }
    }

    public void knockBackSlime(Entity e) {
        Slime slime = (Slime) e;
        slime.setDirection(direction);
        slime.setSpeed(slime.getSpeed()+10);
        slime.setKnockBack(true);
    }

    //draw image
    @Override
    public void draw(GraphicsContext gc) {
        if (isInvincible) {
            gc.setGlobalAlpha(0.3);
        }
        gc.drawImage(def, screenX, screenY, Config.tileSize, Config.tileSize);
        if (direction == "left" || direction == "downleft") {
            gc.drawImage(attackDef, screenX - Config.tileSize, screenY, Config.tileSize, Config.tileSize);
        }
        if (direction == "right" || direction == "upright") {
            gc.drawImage(attackDef, screenX + Config.tileSize, screenY, Config.tileSize, Config.tileSize);
        }
        if (direction == "up" || direction == "upleft") {
            gc.drawImage(attackDef, screenX, screenY - Config.tileSize, Config.tileSize, Config.tileSize);
        }
        if (direction == "down" || direction == "downright") {
            gc.drawImage(attackDef, screenX, screenY + Config.tileSize, Config.tileSize, Config.tileSize);
        }
        gc.setGlobalAlpha(1);
    }

    public int getStrength() {
        return strength;
    }

    public int getLevel() {
        return level;
    }

    public int getDex() {
        return dex;
    }

    public int getAttack() {
        return getStrength() * currentWeapon.getAttackValue();
    }

    public int getDefense() {
        return getDex() * currentShield.getDefenseValue()/5;
    }

    public int getExp() {
        return exp;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public int getMoney() {
        return money;
    }

    public void setCurrentWeapon(BaseWeapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void setCurrentShield(BaseShield currentShield) {
        this.currentShield = currentShield;
    }

    //setter
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public BaseWeapon getCurrentWeapon() {
        return currentWeapon;
    }

    public BaseShield getCurrentShield() {
        return currentShield;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getBuffSpeed() {
        return buffSpeed;
    }

    public void setBuffSpeed(int buffSpeed) {
        this.buffSpeed = buffSpeed;
    }

    public int getBuffDex() {
        return buffDex;
    }

    public void setBuffDex(int buffDex) {
        this.buffDex = buffDex;
    }

    public int getBuffStrength() {
        return buffStrength;
    }

    public void setBuffStrength(int buffStrength) {
        this.buffStrength = buffStrength;
    }

    public int getDefaultStrength() {
        return defaultStrength;
    }

    public void setDefaultStrength(int defaultStrength) {
        this.defaultStrength = defaultStrength;
    }

    public int getDefaultSpeed() {
        return defaultSpeed;
    }

    public void setDefaultSpeed(int defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }

    public int getDefaultDex() {
        return defaultDex;
    }

    public void setDefaultDex(int defaultDex) {
        this.defaultDex = defaultDex;
    }
}
