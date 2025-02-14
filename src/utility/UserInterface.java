package utility;

import config.Config;
import config.Status;
import interfacep.Storable;
import javafx.scene.image.Image;
import object.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import object.items.BaseHammer;
import object.potion.Potion;
import object.weapon.BaseShield;
import object.weapon.BaseWeapon;
import panel.GamePanel;

import java.util.Objects;

public class UserInterface {
    private GamePanel gp;
    private Font customFont;

    private Player p;
    public static int leftSlotCol = 0;
    public static int leftSlotRow = 0;
    public static int rightSlotCol = 0;
    public static int rightSlotRow = 0;
    private Image defenseImage = new Image(ClassLoader.getSystemResourceAsStream("image/items/def.png"));
    private Image speedImage = new Image(ClassLoader.getSystemResourceAsStream("image/items/speed.png"));
    private Image attackImage = new Image(ClassLoader.getSystemResourceAsStream("image/items/atk.png"));


    public UserInterface(GamePanel gp) {
        this.gp = gp;
        p = Player.getInstance();
        customFont = new Font("Georgia",20);
    }

    public void drawHeart(GraphicsContext gc) {
        int heart = p.getLife();
        int leftheart = p.getMaxLife();
        int big = p.getLife()/2;
        int bigleftheart = p.getMaxLife()/2;
        boolean half = !(heart%2==0);
        boolean deadhalf = !(leftheart%2==0);
        int i=0;
        while (i<big) {
            gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("image/heart/fullheart.png")),i*25,10);
            i++;
        }
        if (half) {
            gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("image/heart/halfheart.png")),i*25,10);
            gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("image/heart/halfdeadheart.png")),i*25+11,10);
            i++;
        }
        while (i<bigleftheart) {
            gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("image/heart/deadheart.png")),i*25,10);
            i++;
        }
        if (deadhalf) {
            gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("image/heart/halfdeadheartl.png")),i*25,10);
        }
    }

    public void draw(GraphicsContext gc) {
        if (InputUtility.isKeyPressed(KeyCode.R)) {
            if (InputUtility.getKeyPressed().contains(KeyCode.ENTER)) {
                setAction();
            }
            drawCharacterScreen(gc);
            drawInventory(p,gc,"right");
        }
        drawMoney(gc,customFont,p);
        drawHeart(gc);
        drawStatus(gc);
    }

    public void setAction(){
        try {
            if (p.getInventory().get(getItemIndexOnSlot("right")) instanceof BaseWeapon) {
                p.getInventory().add(p.getCurrentWeapon());
                p.setCurrentWeapon((BaseWeapon) p.getInventory().get(getItemIndexOnSlot("right")));
                p.getInventory().remove(getItemIndexOnSlot("right"));
            } else if (p.getInventory().get(getItemIndexOnSlot("right")) instanceof BaseShield) {
                p.getInventory().add(p.getCurrentShield());
                p.setCurrentShield((BaseShield) p.getInventory().get(getItemIndexOnSlot("right")));
                p.getInventory().remove(getItemIndexOnSlot("right"));
            } else if (p.getInventory().get(getItemIndexOnSlot("right")) instanceof Potion) {
                ((Potion) p.getInventory().get(getItemIndexOnSlot("right"))).use( p);
                p.getInventory().remove(getItemIndexOnSlot("right"));
            } else if (p.getInventory().get(getItemIndexOnSlot("right")) instanceof BaseHammer) {
                ((BaseHammer) p.getInventory().get(getItemIndexOnSlot("right"))).use();
                p.getInventory().remove(getItemIndexOnSlot("right"));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    public void drawStatus(GraphicsContext gc) {
        if (Status.speedBuff) {
            gc.drawImage(speedImage,0,30,Config.tileSize,Config.tileSize);
        } if (Status.strengthBuff) {
            gc.drawImage(attackImage,0,70,Config.tileSize,Config.tileSize);
        } if (Status.dexBuff) {
            gc.drawImage(defenseImage,0,110,Config.tileSize,Config.tileSize);
        }
    }

    public void drawCharacterScreen(GraphicsContext gc) {
        final int frameX = Config.tileSize;
        final int frameY = Config.tileSize;
        final int frameWidth = Config.tileSize * 5;
        final int frameHeight = Config.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, gc);
        int x = frameX;
        int y = frameY;
        int last = x + frameWidth - 50;
        drawStatusText(x, y, last, gc);
    }

    public static void drawSubWindow(int x, int y, int width, int height, GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.5);
        gc.fillRoundRect(x, y, width, height, 35, 35);
        gc.setGlobalAlpha(1);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 50, 50);
        gc.strokeRoundRect(x + 1, y + 1, width - 1, height - 1, 25, 25);
        gc.setLineWidth(1);
    }

    public void drawStatusText(int x, int y, int last, GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(customFont);
        setStatusTextTitile(x, y, gc);

        gc.setFill(Color.WHITE);
        setStatusText(x, y, last,gc);

        gc.drawImage(p.getCurrentWeapon().getImage(), last - 20, y + Config.tileSize * 7 + 15, Config.tileSize, Config.tileSize);
        gc.drawImage(p.getCurrentShield().getImage(), last - 20, y + Config.tileSize * 8 + 15, Config.tileSize, Config.tileSize);

    }

    public void setStatusTextTitile(int x, int y, GraphicsContext gc) {
        gc.fillText("Level", x + 20, y + Config.tileSize);
        gc.fillText("STR", x + 20, y + Config.tileSize * 2);
        gc.fillText("DEX", x + 20, y + Config.tileSize * 3);
        gc.fillText("ATK", x + 20, y + Config.tileSize * 4);
        gc.fillText("DEF", x + 20, y + Config.tileSize * 5);
        gc.fillText("EXP", x + 20, y + Config.tileSize * 6);
        gc.fillText("Next LV EXP", x + 20, y + Config.tileSize * 7);
        gc.fillText("Weapon", x + 20, y + Config.tileSize * 8);
        gc.fillText("Shield", x + 20, y + Config.tileSize * 9);
    }

    public void setStatusText(int x, int y, int last, GraphicsContext gc) {
        gc.fillText("" + p.getLevel(), last, y + Config.tileSize);
        gc.fillText("" + p.getStrength(), last, y + Config.tileSize * 2);
        gc.fillText("" + p.getDex(), last, y + Config.tileSize * 3);
        gc.fillText("" + p.getAttack(), last, y + Config.tileSize * 4);
        gc.fillText("" + p.getDefense(), last, y + Config.tileSize * 5);
        gc.fillText("" + p.getExp(), last, y + Config.tileSize * 6);
        gc.fillText("" + p.getNextLevelExp(), last, y + Config.tileSize * 7);
    }

    public static void drawMoney(GraphicsContext gc,Font font,Player p) {
        gc.setFill(Color.WHITE);
        gc.setFont(font);
        gc.setFont(new Font(40));
        gc.fillText("Money : "+p.getMoney(),Config.tileSize*27,Config.tileSize*17);
        gc.setFont(new Font(20));
    }

    public static String drawInventory(Storable e,GraphicsContext gc,String side) {
        int frameX = checkSide(side);
        int frameY = Config.tileSize*3;
        int frameWidth = Config.tileSize * 6;
        int frameHeight = Config.tileSize * 5;
        UserInterface.drawSubWindow(frameX, frameY, frameWidth, frameHeight, gc);

        drawSlot(frameX + 20, frameY + 20, e, gc);
        drawCursor(side, frameX + 20, frameY + 20, gc);

        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = Config.tileSize*3;
        //description
        int textX = dFrameX +20;
        int textY = dFrameY+Config.tileSize;

        int itemIndex = getItemIndexOnSlot(side);
        if(itemIndex<e.getInventory().size()) {
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight,gc);
            gc.setFill(Color.WHITE);
            gc.fillText(e.getInventory().get(itemIndex).getDescription(),textX,textY);
            gc.setFill(Color.BLACK);
        }
        return side;
    }

    public static int checkSide(String side) {
        if(side.equals("right")) {
            return Config.tileSize * 25;
        } else {
            return Config.tileSize;
        }
    }

    public static void drawSlot(int SLOT_X_START, int SLOT_Y_START, Storable e, GraphicsContext gc) {
        int slotX = SLOT_X_START;
        int slotY = SLOT_Y_START;

        for(int i=0;i<e.getInventory().size();i++) {
            gc.drawImage(e.getInventory().get(i).getImage(),slotX,slotY,Config.tileSize,Config.tileSize);

            slotX+=Config.tileSize;
            if(i==4||i==9||i==14) {
                slotX=SLOT_X_START;
                slotY+=Config.tileSize;
            }
        }
    }

    public static void drawCursor(String side, int slotXStart, int slotYStart, GraphicsContext gc) {
        int cursorX,cursorY;
        //cursor
        if (side.equals("left")){
            cursorX = slotXStart + (Config.tileSize * UserInterface.leftSlotCol);
            cursorY = slotYStart + (Config.tileSize * UserInterface.leftSlotRow);
        } else {
            cursorX = slotXStart + (Config.tileSize * UserInterface.rightSlotCol);
            cursorY = slotYStart + (Config.tileSize * UserInterface.rightSlotRow);
        }
        int cursorWidth = Config.tileSize;
        int cursorHeight = Config.tileSize;
        // Draw cursor
        if ((Objects.equals(side, "left") &&InputUtility.getKeyPressed().contains(KeyCode.J))){
            gc.setFill(Color.WHITE);
            gc.setLineWidth(3);
            gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
            gc.setLineWidth(1);
        } else if (Objects.equals(side, "right") &&!InputUtility.getKeyPressed().contains(KeyCode.J)) {
            gc.setFill(Color.WHITE);
            gc.setLineWidth(3);
            gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
            gc.setLineWidth(1);
        }
    }

    public static int getItemIndexOnSlot(String side) {
        int itemIndex;
        if (side=="left") {
            itemIndex = UserInterface.leftSlotCol+(UserInterface.leftSlotRow*5);
        } else {
            itemIndex = UserInterface.rightSlotCol+(UserInterface.rightSlotRow*5);
        }
        return itemIndex;
    }
}
