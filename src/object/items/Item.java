package object.items;

import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import object.OBJ;
import object.Player;
import panel.GamePanel;


public abstract class Item extends OBJ {
    protected Image image;
    protected String name;
    protected String description;
    protected boolean isCollision = false;
    protected boolean isInteracted;
    protected Player player;
    protected Rectangle solidArea = new Rectangle(0,0,Config.tileSize,Config.tileSize);
    protected int solidAreaDefaultX = 0;
    protected int solidAreaDefaultY = 0;

    public Item() {
        gp = GamePanel.getInstance();
        setVisible(true);
        setPlayer(Player.getInstance());
        setDestroyed(false);
    }

    @Override
    public void draw(GraphicsContext gc) {

        int screenX = worldX - player.getWorldX() + player.getScreenX();
        int screenY = worldY - player.getWorldY() + player.getScreenY();

        if (worldX + Config.tileSize > player.getWorldX() - player.getScreenX() &&
                worldX - Config.tileSize < player.getWorldX() + player.getScreenX() &&
                worldY + Config.tileSize > player.getWorldY() - player.getScreenY() &&
                worldY - Config.tileSize < player.getWorldY() + player.getScreenY()) {
            gc.drawImage(image, screenX, screenY, Config.tileSize, Config.tileSize);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return isCollision;
    }

    public void setCollision(boolean collision) {
        this.isCollision = collision;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public boolean isInteracted() {
        return isInteracted;
    }
    public void update() {}

    public void setInteracted(boolean interacted) {
        this.isInteracted = interacted;
    }
}
