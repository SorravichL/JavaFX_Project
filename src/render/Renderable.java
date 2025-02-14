package render;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {
    //field (interface no need to declare as public)
    int getZ();//deep
    void draw(GraphicsContext gc);
    boolean isVisible();
    boolean isDestroyed();
}
