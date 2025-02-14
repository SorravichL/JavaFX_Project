package object.items;

import config.Config;
import config.GameState;
import interfacep.Storable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import object.Player;
import object.potion.HealthPotion;
import utility.InputUtility;
import utility.UserInterface;

import java.util.ArrayList;

import static object.Trader.drawInfoScreen;

public class Chest extends Item implements Storable {
    private ArrayList<Item> inventory;
    private GraphicsContext gc = gp.getGraphicsContext2D();
    private Player player = Player.getInstance();

    public Chest(int x, int y) {
        super();
        inventory = new ArrayList<>();
        inventory.add(new HealthPotion());
        z=1;
        name = "Chest";
        image = new Image(ClassLoader.getSystemResourceAsStream("image/objects/normal_chest.png"));
        setWorldX(x * Config.tileSize);
        setWorldY(y * Config.tileSize);
        setCollision(true);
    }

    public void update() {
        if (isInteracted()) {
            GameState.isChestState=true;
            drawInfoScreen(gc);
            gc.setFill(Color.WHITE);
            gc.fillText("[Press J to change side of the windows]",Config.tileSize*14.35, Config.tileSize*2);
            gc.fillText("[Press ENTER to transfer the items]",Config.tileSize*14.85, Config.tileSize*3);
            drawStoreFrame();
            if (InputUtility.getKeyPressed().contains(KeyCode.ENTER)&&GameState.isChestState) {
                if (InputUtility.getKeyPressed().contains(KeyCode.J)) {
                    pickUp("left");
                } else {
                    pickUp("right");
                }
            }
        } else {
            GameState.isChestState=false;
        }
        setInteracted(false);
    }

    private void drawStoreFrame() {
        UserInterface.drawInventory(this,gp.getGraphicsContext2D(),"left");
        UserInterface.drawInventory(player,gp.getGraphicsContext2D(),"right");
    }

    private void pickUp(String side) {
        Item target;
        if (side=="right") {
            target = player.getInventory().get(UserInterface.getItemIndexOnSlot("right"));
            player.getInventory().remove(UserInterface.getItemIndexOnSlot("right"));
            this.getInventory().add(target);
        } else {
            target = this.getInventory().get(UserInterface.getItemIndexOnSlot("left"));
            this.getInventory().remove(UserInterface.getItemIndexOnSlot("left"));
            player.getInventory().add(target);
        }
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
