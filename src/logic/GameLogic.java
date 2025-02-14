// Logic for game
package logic;

import config.Config;
import config.GameState;
import object.*;
import object.OBJ;
import object.items.Chest;
import object.items.House;
import object.items.Item;
import object.monster.BlueSlime;
import object.monster.PinkSlime;
import object.monster.YellowSlime;
import panel.GameOverPane;
import render.RenderableHolder;
import utility.CollisionChecker;
import utility.ObjectSetter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class GameLogic {
    private int day;
    private int spawnCounter = 0;
    private int slimeCounter = 0;

    //container for entity and object
    private List<OBJ> gameObjectContainer;
    public ObjectSetter objectSetter;
    public CollisionChecker collisionChecker;
    //each
    private Player player;
    public static ArrayList<Entity> slimeList;
    private Chest chest1;
    private RenderableHolder renderableHolder;
    private House house;
    public static GameLogic instance;
    public static GameLogic getInstance() {
        instance = new GameLogic();
        return instance;
    }

    //constructor(setup for all entity)
    public GameLogic() {
        gameObjectContainer = new ArrayList<>();
        player = Player.getInstance();
        objectSetter = new ObjectSetter(this);
        collisionChecker = new CollisionChecker(null,this);
        slimeList = new ArrayList<>(20);
        renderableHolder = RenderableHolder.getInstance();
        house = House.getInstance();
        chest1 = new Chest(26+ Config.fixedPosition,22+ Config.fixedPosition);
        addNewObject(player);
        addNewObject(chest1);
        setupGame();
    }

    private void setupGame() {
        objectSetter.setObject();
    }

    //function for add object to container
    public void addNewObject(OBJ object) {
        gameObjectContainer.add(object);
        renderableHolder.add(object);
    }
    public void removeObject(OBJ object) {
        gameObjectContainer.remove(object);
        renderableHolder.remove(object);
    }

    //fetch
    public void logicUpdate() {
        GameState.update();
        setSpawnCounter(getSpawnCounter() + 1);
        checkState();
        allObjectUpdate();
        int objIndex = collisionChecker.checkObject(player,true);
        pickUpObject(objIndex);
        slimeCheck();
        house.update();
        checkHit();
    }
    private void checkState() {
        if (GameState.isNightState) {
            if (getSlimeCounter() < day * 2 && getSpawnCounter() > 120) {
                setSpawnCounter(0);
                addSlime();
            }
        }
        if (!GameState.isNightState) {
            clearSlime();
            setSlimeCounter(0);
            day = Config.day;
        }
    }
    private void allObjectUpdate() {
        for(OBJ e:gameObjectContainer) {
            if (e instanceof Entity ee) {
                if (((Entity) e).isAlive()) {
                    ee.update();
                }
            } else if(e instanceof Item ee) {
                ee.update();
            }else {
                System.out.println("dunno this instance");
            }
        }
    }
    private void slimeCheck() {
        try {
            for (Entity e : slimeList) {
                if (e.getLife() <= 0) {
                    e.setDying(true);
                }
                if (!e.isAlive()) {
                    removeObject(e);
                    slimeList.remove(e);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }
    }

    private void pickUpObject(int i) {
        if (i != 999) {
            if(!((Item) getGameObjectContainer().get(i)).isCollision()){
                player.getInventory().add((Item) getGameObjectContainer().get(i));
                renderableHolder.getObjects().remove(getGameObjectContainer().get(i));
                getGameObjectContainer().remove(i);
            }
        }
    }
    private void checkHit() {
        boolean contactPlayer, contactHouse;
        for (Entity e : slimeList) {
            collisionChecker.checkSlime(e, slimeList);
            contactPlayer = collisionChecker.checkPlayer(e);
            contactHouse = collisionChecker.checkHouse(e, house);
            if (contactPlayer && !player.isInvincible()) {
                player.setLife(player.getLife() - e.getAttack());
                player.setInvincible(true);
            }
            if (contactHouse && !house.isInvincible()) {
                house.setLife(house.getLife() - e.getAttack());
                house.setInvincible(true);
            }
            checkGameOver();
        }
    }

    private void checkGameOver() {
        if (player.getLife() <= 0 || house.getLife() <= 0) {
            GameOverPane.gameOver();
        } else {
            // do nothing
        }
    }

    public void addSlime() {
        if (Config.day < 5) {
            firstPhase();
        } else if (Config.day >= 5 && Config.day < 10) {
            secondPhase();
        } else if (Config.day >= 10) {
            thirdPhase();
        }
        setSlimeCounter(getSlimeCounter() + 1);
    }
    private void firstPhase() {
        if (getSlimeCounter()%4 == 0) {
            slimeList.add(new BlueSlime(25, 10));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter()%4 == 1) {
            slimeList.add(new BlueSlime(50, 10));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter()%4 == 2) {
            slimeList.add(new BlueSlime(25, 50));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter()%4 == 3) {
            slimeList.add(new BlueSlime(10, 25));
            addNewObject(slimeList.get(getSlimeCounter()));
        }
    }
    private void secondPhase() {
        if (getSlimeCounter() % 4 == 0) {
            slimeList.add(new YellowSlime(25, 10));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter() % 4 == 1) {
            slimeList.add(new YellowSlime(50, 10));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter() % 4 == 2) {
            slimeList.add(new YellowSlime(25, 50));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter() % 4 == 3) {
            slimeList.add(new YellowSlime(10, 25));
            addNewObject(slimeList.get(getSlimeCounter()));
        }
    }
    private void thirdPhase() {
        if (getSlimeCounter() % 4 == 0) {
            slimeList.add(new PinkSlime(25, 10));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter() % 4 == 1) {
            slimeList.add(new PinkSlime(50, 10));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter() % 4 == 2) {
            slimeList.add(new PinkSlime(25, 50));
            addNewObject(slimeList.get(getSlimeCounter()));
        } else if (getSlimeCounter() % 4 == 3) {
            slimeList.add(new PinkSlime(10, 25));
            addNewObject(slimeList.get(getSlimeCounter()));
        }
    }

    public void clearSlime() {
        if (!slimeList.isEmpty()) {
            for (int i = 0; i < slimeList.size(); i++) {
                removeObject(slimeList.get(i));
            }
            slimeList.clear();
            setSlimeCounter(0);
        }
    }

    //getter
    public Player getPlayer() {
        return player;
    }

    public List<OBJ> getGameObjectContainer() {
        return gameObjectContainer;
    }

    public Chest getChest1() {
        return chest1;
    }

    public ArrayList<Entity> getSlimeList() {
        return slimeList;
    }

    public int getSpawnCounter() {
        return spawnCounter;
    }

    public void setSpawnCounter(int spawnCounter) {
        this.spawnCounter = spawnCounter;
    }

    public int getSlimeCounter() {
        return slimeCounter;
    }

    public void setSlimeCounter(int slimeCounter) {
        this.slimeCounter = slimeCounter;
    }
}

