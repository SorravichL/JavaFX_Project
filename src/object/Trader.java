package object;

import config.Config;
import config.GameState;
import interfacep.Sellable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import object.items.NormalHammer;
import object.items.Item;
import object.items.SuperHammer;
import object.potion.DexPotion;
import object.potion.HealthPotion;
import object.potion.SpeedPotion;
import object.potion.StrengthPotion;
import object.weapon.*;
import utility.InputUtility;
import utility.UserInterface;

import java.util.ArrayList;

public class Trader extends Item {
    public static int optionCol = 0;
    public static int buyingRow = 0;
    public static int counter = 0;
    private Player player = Player.getInstance();
    private GraphicsContext gc = gp.getGraphicsContext2D();
    private ArrayList<Item> sellingItem;
    private Font customFont = new Font("Georgia", 20);

    public Trader() {
        super();
        z = 10;
        name = "Trader";
        solidArea = new Rectangle(0, 0, Config.tileSize * 4, Config.tileSize * 3);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/objects/Trader.png"));
        setWorldX(64 * Config.tileSize);
        setWorldY(66 * Config.tileSize);
        setCollision(true);
        setItem();
    }

    public void setItem() {
        sellingItem = new ArrayList<>();
        sellingItem.add(new HealthPotion());
        sellingItem.add(new SpeedPotion());
        sellingItem.add(new DexPotion());
        sellingItem.add(new StrengthPotion());
        sellingItem.add(new IronSword());
        sellingItem.add(new IronShield());
        sellingItem.add(new PowerfulSword());
        sellingItem.add(new PowerfulShield());
        sellingItem.add(new LegendarySword());
        sellingItem.add(new LegendaryShield());
        sellingItem.add(new NormalHammer());
        sellingItem.add(new SuperHammer());
    }

    @Override
    public void draw(GraphicsContext gc) {

        int screenX = worldX - player.getWorldX() + player.getScreenX();
        int screenY = worldY - player.getWorldY() + player.getScreenY();

        if (worldX + Config.tileSize > player.getWorldX() - player.getScreenX() &&
                worldX - Config.tileSize < player.getWorldX() + player.getScreenX() &&
                worldY + Config.tileSize > player.getWorldY() - player.getScreenY() &&
                worldY - Config.tileSize < player.getWorldY() + player.getScreenY()) {
            gc.drawImage(image, screenX, screenY, Config.tileSize * 5, Config.tileSize * 3);
        }
    }

    public void update() {
        if (GameState.isNightState) {
            setVisible(false);
            setCollision(false);
        } else {
            setVisible(true);
            setCollision(true);
        }
        if (isVisible()){
            checkPage();
            if (isInteracted()) {
                GameState.isTraderState = true;
                drawTradeFrame();
            } else {
                GameState.isTraderState = false;
                GameState.isChooseState = false;
            }
            setInteracted(false);
        }
    }

    private void checkPage() {
        GameState.traderPage=(int) (double) (buyingRow / 3+1);
    }


    public void drawTradeFrame() {
        UserInterface.drawMoney(gc, customFont, player);
        if (GameState.isChooseState) {
            UserInterface.drawInventory(player, gc, "right");
            UserInterface.drawMoney(gc, customFont, player);
            if (optionCol == 0) {//buy
                drawBuyScreen();
            } else if (optionCol == 1) {//sell
                drawSellScreen();
            }
        } else {
            drawOptionFrame();
            if (InputUtility.getKeyPressed().contains(KeyCode.ENTER)) {
                GameState.isChooseState = true;
            }
        }
    }

    public void drawSellScreen() {
        int value;
        if (UserInterface.getItemIndexOnSlot("right") >= player.getInventory().size()) {
            value = 0;
        } else {
            value = ((Sellable) (player.getInventory().get(UserInterface.getItemIndexOnSlot("right")))).getPrice();
        }
        UserInterface.drawSubWindow(Config.tileSize, Config.tileSize, 10 * Config.tileSize, 3 * Config.tileSize, gc);
        if (InputUtility.getKeyPressed().contains(KeyCode.SPACE)) {
            counter++;
            if (counter > 1) {
                counter = 0;
                sell();
            }
        }
        gc.setFill(Color.WHITE);
        gc.fillText("[Press SPACE on slot you wish to sell]", Config.tileSize * 2.5, Config.tileSize * 2);
        gc.fillText("Price For Sell : " + value, Config.tileSize * 4.5, Config.tileSize * 3);

    }

    public static void drawInfoScreen(GraphicsContext gc) {
        UserInterface.drawSubWindow(Config.tileSize*13, Config.tileSize, 10 * Config.tileSize, 3 * Config.tileSize, gc);
    }

    private void drawBuyScreen() {
        drawInfoScreen(gc);
        gc.setFill(Color.WHITE);
        gc.fillText("[Press SPACE on slot you wish to buy]",Config.tileSize*14.5, Config.tileSize*2);
        gc.fillText("[Press J to change side of the windows]",Config.tileSize*14.35, Config.tileSize*3);
        UserInterface.drawSubWindow(Config.tileSize, Config.tileSize, 10 * Config.tileSize, 11 * Config.tileSize, gc);
        selectedItem(buyingRow % 3);
        showItem(GameState.traderPage-1);
        //item index = buying row
        gc.fillText(""+GameState.traderPage,Config.tileSize+15, Config.tileSize+30);
        if (InputUtility.getKeyPressed().contains(KeyCode.SPACE)) {
            counter++;
            if (counter > 1) {
                counter = 0;
                if (player.getInventory().size()<Config.inventorySize) {
                    buy();
                }
            }
        }
    }

    private void buy() {
        if (InputUtility.getKeyPressed().contains(KeyCode.J)) {
            Sellable target;
            target = (Sellable) sellingItem.get(buyingRow);
            if (player.getMoney() >= target.getPrice()) {
                player.setMoney(player.getMoney() - target.getPrice());
                player.getInventory().add((Item) target);
            }
        }
    }

    private void showItem(int page) {
        gc.setFill(Color.WHITE);
        if (page * 3 < sellingItem.size()) {
            gc.drawImage(sellingItem.get(page * 3).getImage(), Config.tileSize + 40, Config.tileSize + 50, Config.tileSize * 2, Config.tileSize * 2);
            gc.fillText("Price : "+((Sellable) sellingItem.get(page*3)).getPrice(),4*Config.tileSize + 40,Config.tileSize + 70);
            gc.fillText(sellingItem.get(page*3).getDescription(),4*Config.tileSize + 40,Config.tileSize*2 + 50);
        }
        if (page * 3 + 1 < sellingItem.size()) {
            gc.drawImage(sellingItem.get(page * 3 + 1).getImage(), Config.tileSize + 40, Config.tileSize*3 + 120, Config.tileSize * 2, Config.tileSize * 2);
            gc.fillText("Price : "+((Sellable) sellingItem.get(page*3+1)).getPrice(),4*Config.tileSize + 40,Config.tileSize*3 + 135);
            gc.fillText(sellingItem.get(page*3+1).getDescription(),4*Config.tileSize + 40,Config.tileSize*5 + 65);
        }
        if (page * 3 + 2 < sellingItem.size()) {
            gc.drawImage(sellingItem.get(page * 3 + 2).getImage(), Config.tileSize + 40, Config.tileSize*6 + 150, Config.tileSize * 2, Config.tileSize * 2);
            gc.fillText("Price : "+((Sellable) sellingItem.get(page*3+2)).getPrice(),4*Config.tileSize + 40,Config.tileSize*6 + 170);
            gc.fillText(sellingItem.get(page*3+2).getDescription(),4*Config.tileSize + 40,Config.tileSize*9 + 55);
        }
    }

    private void selectedItem(int pos) {
        if (InputUtility.getKeyPressed().contains(KeyCode.J)) {
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.strokeRoundRect(Config.tileSize + 20, Config.tileSize + (Config.tileSize * 3 * pos) + 25 + 25 * pos, 10 * Config.tileSize - 40, Config.tileSize * 3, 50, 50);
        }
    }

    private int sell() {
        Sellable target;
        int price;
        target = (Sellable) (player.getInventory().get(UserInterface.getItemIndexOnSlot("right")));
        player.setMoney(player.getMoney() + target.getPrice());
        price = target.getPrice();
        player.getInventory().remove(UserInterface.getItemIndexOnSlot("right"));
        return price;
    }

    public void drawOptionFrame() {
        int frameX = 1;
        int frameY = 1;
        int frameWidth = 4;
        int frameHeight = 3;
        int slotXStart = frameX;
        int slotYStart = frameY;
        int cursorWidth = Config.tileSize * frameWidth - 30;
        int cursorHeight = Config.tileSize - 10;
        int cursorX = slotXStart * Config.tileSize + 15;
        int cursorY = (slotYStart * Config.tileSize + (Config.tileSize * optionCol)) + 20;
        UserInterface.drawSubWindow(Config.tileSize * frameX, Config.tileSize * frameY, Config.tileSize * frameWidth, Config.tileSize * frameHeight, gc);
        gc.setFill(Color.WHITE);
        gc.setLineWidth(3);
        gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        gc.setLineWidth(1);
        gc.setFont(customFont);
        //Option 1
        gc.fillText("Buy", Config.tileSize * (frameX) + 20, Config.tileSize * (frameY + 1));
        //Option 2
        gc.fillText("Sell", Config.tileSize * (frameX) + 20, Config.tileSize * (frameY + 2));
    }
}
