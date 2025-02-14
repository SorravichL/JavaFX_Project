package tile;

import config.Config;
import object.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import panel.GamePanel;

import javax.xml.namespace.QName;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GamePanel gp;
    private Player player;
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.player = Player.getInstance();
        tile = new Tile[178];
        mapTileNum = new int[Config.maxWorldCol][Config.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Land1.png"));
        tile[1] = new Tile();
        tile[1].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Land4.png"));
        tile[2] = new Tile();
        tile[2].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Land5.png"));
        tile[3] = new Tile();
        tile[3].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Land7.png"));
        tile[4] = new Tile();
        tile[4].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/rock.png"));
        tile[5] = new Tile();
        tile[5].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Land6.png"));
        tile[6] = new Tile();
        tile[6].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Leaf.png"));
        tile[7] = new Tile();
        tile[7].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/wood.png"));
        tile[7].collision=true;
        tile[8] = new Tile();
        tile[8].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/patravee.png"));
        tile[8].collision=true;
        tile[9] = new Tile();
        tile[9].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Sea2.png"));
        tile[9].collision=true;
        tile[10] = new Tile();
        tile[10].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Sea3.png"));
        tile[10].collision=true;
        tile[11] = new Tile();
        tile[11].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Sea4.png"));
        tile[11].collision=true;
        tile[12] = new Tile();
        tile[12].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/SeaTop.png"));
        tile[12].collision=true;
        tile[13] = new Tile();
        tile[13].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/SeaRight.png"));
        tile[13].collision=true;
        tile[14] = new Tile();
        tile[14].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/SeaBottom.png"));
        tile[14].collision=true;
        tile[15] = new Tile();
        tile[15].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/SeaLeft.png"));
        tile[15].collision=true;
        tile[16] = new Tile();
        tile[16].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/TopLeft.png"));
        tile[16].collision=true;
        tile[17] = new Tile();
        tile[17].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/TopRight.png"));
        tile[17].collision=true;
        tile[18] = new Tile();
        tile[18].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/BottomRight.png"));
        tile[18].collision=true;
        tile[19] = new Tile();
        tile[19].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/BottomLeft.png"));
        tile[19].collision=true;
        setHouse();
        tile[120] = new Tile();
        tile[120].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 11.png"));
        tile[120].collision=true;
        tile[121] = new Tile();
        tile[121].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 12.png"));
        tile[121].collision=true;
        tile[122] = new Tile();
        tile[122].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 13.png"));
        tile[122].collision=true;
        tile[123] = new Tile();
        tile[123].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 21.png"));
        tile[123].collision=true;
        tile[124] = new Tile();
        tile[124].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 22.png"));
        tile[124].collision=true;
        tile[125] = new Tile();
        tile[125].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 23.png"));
        tile[125].collision=true;
        tile[126] = new Tile();
        tile[126].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 31.png"));
        tile[126].collision=true;
        tile[127] = new Tile();
        tile[127].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 32.png"));
        tile[127].collision=true;
        tile[128] = new Tile();
        tile[128].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Beach 33.png"));
        tile[128].collision=true;
        tile[129] = new Tile();
        tile[129].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 11.png"));
        tile[130] = new Tile();
        tile[130].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 12.png"));
        tile[131] = new Tile();
        tile[131].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 13.png"));
        tile[132] = new Tile();
        tile[132].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 21.png"));
        tile[133] = new Tile();
        tile[133].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 22.png"));
        tile[134] = new Tile();
        tile[134].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 23.png"));
        tile[135] = new Tile();
        tile[135].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 31.png"));
        tile[136] = new Tile();
        tile[136].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 32.png"));
        tile[137] = new Tile();
        tile[137].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Path 33.png"));
        tile[138] = new Tile();
        tile[138].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Pathm.png"));
        tile[139] = new Tile();
        tile[139].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 11.png"));
        tile[140] = new Tile();
        tile[140].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 12.png"));
        tile[141] = new Tile();
        tile[141].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 13.png"));
        tile[142] = new Tile();
        tile[142].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 21.png"));
        tile[143] = new Tile();
        tile[143].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 22.png"));
        tile[144] = new Tile();
        tile[144].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 23.png"));
        tile[145] = new Tile();
        tile[145].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 31.png"));
        tile[146] = new Tile();
        tile[146].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 32.png"));
        tile[147] = new Tile();
        tile[147].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 33.png"));
        tile[148] = new Tile();
        tile[148].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 41.png"));
        tile[148].collision=true;
        tile[149] = new Tile();
        tile[149].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 42.png"));
        tile[149].collision=true;
        tile[150] = new Tile();
        tile[150].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 43.png"));
        tile[150].collision=true;
        tile[151] = new Tile();
        tile[151].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 51.png"));
        tile[151].collision=true;
        tile[152] = new Tile();
        tile[152].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 52.png"));
        tile[152].collision=true;
        tile[153] = new Tile();
        tile[153].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/bridge 53.png"));
        tile[153].collision=true;
        tile[154] = new Tile();
        tile[154].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Pathm2.png"));
        tile[155] = new Tile();
        tile[155].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Pathm3.png"));
        tile[156] = new Tile();
        tile[156].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/Pathm4.png"));
        tile[157] = new Tile();
        tile[157].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/tree1.png"));
        tile[157].collision=true;
        tile[158] = new Tile();
        tile[158].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/tree2.png"));
        tile[158].collision=true;
        tile[159] = new Tile();
        tile[159].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/tree3.png"));
        tile[159].collision=true;
        tile[160] = new Tile();
        tile[160].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/tree4.png"));
        tile[160].collision=true;
        tile[161] = new Tile();
        tile[161].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/tree5.png"));
        tile[161].collision=true;
        tile[162] = new Tile();
        tile[162].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick11.png"));
        tile[162].collision=true;
        tile[163] = new Tile();
        tile[163].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick12.png"));
        tile[163].collision=true;
        tile[164] = new Tile();
        tile[164].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick13.png"));
        tile[164].collision=true;
        tile[165] = new Tile();
        tile[165].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick14.png"));
        tile[165].collision=true;
        tile[166] = new Tile();
        tile[166].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick21.png"));
        tile[166].collision=true;
        tile[167] = new Tile();
        tile[167].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick22.png"));
        tile[167].collision=true;
        tile[168] = new Tile();
        tile[168].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick23.png"));
        tile[168].collision=true;
        tile[169] = new Tile();
        tile[169].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick24.png"));
        tile[169].collision=true;
        tile[170] = new Tile();
        tile[170].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick31.png"));
        tile[170].collision=true;
        tile[171] = new Tile();
        tile[171].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick32.png"));
        tile[171].collision=true;
        tile[172] = new Tile();
        tile[172].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick33.png"));
        tile[172].collision=true;
        tile[173] = new Tile();
        tile[173].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick34.png"));
        tile[173].collision=true;
        tile[174] = new Tile();
        tile[174].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick41.png"));
        tile[174].collision=true;
        tile[175] = new Tile();
        tile[175].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick42.png"));
        tile[175].collision=true;
        tile[176] = new Tile();
        tile[176].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick43.png"));
        tile[176].collision=true;
        tile[177] = new Tile();
        tile[177].image = new Image(ClassLoader.getSystemResourceAsStream("image/tiles/brick44.png"));
        tile[177].collision=true;
        for (int i = 0; i < 178; i++) {
            tile[i].makeScale(gp);
        }
    }

    public void setHouse() {
        int index = 20;
        String name;
        for (int i = 1 ; i <= 10 ; i++) {
            for (int j = 1 ; j <= 10 ; j++) {
                tile[index] = new Tile();
                name = "image/objects/House/row-" + i + "-column-" + j + ".png";
                tile[index].image = new Image(ClassLoader.getSystemResourceAsStream(name));
                if (index >= 20 && index <= 29) {               // Set house top collision
                    tile[index].setCollision(true);
                } else if (index >= 30 && index <= 109) {       // Set house side collision
                    if (index%10 == 0 || index %10 == 9) {
                        tile[index].setCollision(true);
                    }
                } else if (index >= 110 && index <= 119) {      // Set house bottom collision
                    if (index != 115 && index != 116) {         // Set collision except for the house door
                        tile[index].setCollision(true);
                    }
                }
                index++;
            }
        }
    }

    public void loadMap() {
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream("image/maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < Config.maxWorldCol && row < Config.maxWorldRow) {
                String line = br.readLine();

                while (col < Config.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == Config.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext gc) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < Config.maxWorldCol && worldRow < Config.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * Config.tileSize;
            int worldY = worldRow * Config.tileSize;
            int screenX = worldX - player.getWorldX() + player.screenX;
            int screenY = worldY - player.getWorldY() + player.screenY;

            if (worldX + Config.tileSize > player.getWorldX() - player.getScreenX() &&
                    worldX - Config.tileSize < player.getWorldX() + player.getScreenX() &&
                    worldY + Config.tileSize > player.getWorldY() - player.getScreenY() &&
                    worldY - Config.tileSize < player.getWorldY() + player.getScreenY()) {
                gc.drawImage(tile[tileNum].image, screenX, screenY, tile[tileNum].getImageView().getFitWidth(),
                        tile[tileNum].getImageView().getFitHeight());
            }


            worldCol++;

            if (worldCol == Config.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public Tile[] getTile() {
        return tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }
}