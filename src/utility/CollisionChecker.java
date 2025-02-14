package utility;

import config.Config;
import logic.GameLogic;
import object.Entity;
import object.items.House;
import object.items.Item;
import object.Player;
import panel.GamePanel;

import java.util.ArrayList;

public class CollisionChecker {
    private GamePanel gp;
    private GameLogic gl;
    private Player player = Player.getInstance();

    public CollisionChecker(GamePanel gp, GameLogic gl) {
        this.gp = gp;
        this.gl = gl;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.getWorldX() + entity.getSolidArea().getX());
        int entityRightWorldX = (int) (entity.getWorldX() + entity.getSolidArea().getX() + entity.getSolidArea().getWidth());
        int entityTopWorldY = (int) (entity.getWorldY() + entity.getSolidArea().getY());
        int entityBottomWorldY = (int) (entity.getWorldY() + entity.getSolidArea().getY() + entity.getSolidArea().getHeight());

        int entityLeftCol = entityLeftWorldX / Config.tileSize;
        int entityRightCol = entityRightWorldX / Config.tileSize;
        int entityTopRow = entityTopWorldY / Config.tileSize;
        int entityBottomRow = entityBottomWorldY / Config.tileSize;

        int tileNum1 = 0, tileNum2 = 0;
        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                break;
            case "upright":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Config.tileSize;
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                break;
            case "upleft":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Config.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                break;
            case "downright":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Config.tileSize;
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                break;
            case "downleft":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Config.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                break;
        }
        if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
            entity.setCollisionOn(true);
        }
    }


    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gl.getGameObjectContainer().size(); i++) {
            if (gl.getGameObjectContainer().get(i) instanceof Item) {
                Item e = (Item) gl.getGameObjectContainer().get(i);
                if (gl.getGameObjectContainer().get(i) != null) {
                    entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
                    entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
                    e.getSolidArea().setX(e.getWorldX() + e.getSolidArea().getX());
                    e.getSolidArea().setY(e.getWorldY() + e.getSolidArea().getY());

                    setEntitySolidArea(entity);

                    if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                        if (e.isCollision() && !(e instanceof  House)) {
                            entity.setCollisionOn(true);
                        }
                        if (player) {
                            index = i;
                        }
                        e.setInteracted(true);
                    }
                }
                entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
                entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
                e.getSolidArea().setX(e.getSolidAreaDefaultX());
                e.getSolidArea().setY(e.getSolidAreaDefaultY());
            }
        }
        return index;
    }

    public ArrayList<Integer> checkSlime(Entity entity, ArrayList<Entity> target) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != null) {
                entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
                entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
                target.get(i).getSolidArea().setX(target.get(i).getWorldX() + target.get(i).getSolidArea().getX());
                target.get(i).getSolidArea().setY(target.get(i).getWorldY() + target.get(i).getSolidArea().getY());

                setEntitySolidArea(entity);

                if (entity.getSolidArea().getBoundsInParent().intersects(target.get(i).getSolidArea().getBoundsInParent())) {
                    if (target.get(i) != entity) {
                        entity.setCollisionOn(true);
                        index.add(i);
                    }
                }

                entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
                entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
                target.get(i).getSolidArea().setX(target.get(i).getSolidAreaDefaultX());
                target.get(i).getSolidArea().setY(target.get(i).getSolidAreaDefaultY());
            }
        }
        return index;
    }


    public boolean checkPlayer (Entity entity) {
        boolean contactPlayer = false;
        // Get entity's solid area
        entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
        entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
        // Get object's solid area
        player.getSolidArea().setX(player.getWorldX() + player.getSolidArea().getX());
        player.getSolidArea().setY(player.getWorldY() + player.getSolidArea().getY());

        setEntitySolidArea(entity);

        if (entity.getSolidArea().getBoundsInParent().intersects(player.getSolidArea().getBoundsInParent())) {
            entity.setCollisionOn(true);
            contactPlayer = true;
        }

        entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
        entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
        player.getSolidArea().setX(player.getSolidAreaDefaultX());
        player.getSolidArea().setY(player.getSolidAreaDefaultY());

        return contactPlayer;
    }

    public boolean checkHouse (Entity entity, House house) {
        boolean contactHouse = false;

        entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
        entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
        house.getSolidArea().setX(house.getWorldX() + house.getSolidArea().getX());
        house.getSolidArea().setY(house.getWorldY() + house.getSolidArea().getY());

        setEntitySolidArea(entity);

        if (entity.getSolidArea().getBoundsInParent().intersects(house.getSolidArea().getBoundsInParent())) {
            entity.setCollisionOn(true);
            contactHouse = true;
        }

        entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
        entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
        house.getSolidArea().setX(house.getSolidAreaDefaultX());
        house.getSolidArea().setY(house.getSolidAreaDefaultY());

        return contactHouse;
    }

    public void setEntitySolidArea(Entity entity) {
        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                break;
            case "down":
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "left":
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                break;
            case "right":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                break;
            case "upleft":
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                break;
            case "downright":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "downleft":
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "upright":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                break;
        }
    }
}
