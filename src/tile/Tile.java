package tile;

import config.Config;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import panel.GamePanel;

public class Tile {
    public Image image;
    public ImageView imageView;
    public boolean collision = false;
    public void makeScale(GamePanel gp) {
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(Config.tileSize);
        this.imageView.setFitHeight(Config.tileSize);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}
