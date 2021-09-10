package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.graphics.AnimatedTexture;
import io.github.minetrinity.game.graphics.components.GOverlay;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.io.Resources;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;

public class Camera extends GOverlay {

    public int x = 0;
    public int y = 0;

    private final int factor = 1;
    private final int size = 16;

    private int rows, columns;

    private Entity follow;

    public Camera(Entity follow) {
        setVisible(true);
        for (int y = 0; y < World.getCurrent().getHeight(); y++) {
            for (int x = 0; x < World.getCurrent().getWidth(); x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = (Texture) Resources.getResource(World.getCurrent().getTiles()[x][y][layer].getTexture());
                        if (tempt == null) {
                            System.err.println(World.getCurrent().getTiles()[x][y][layer].getTexture());
                            continue;
                        }
                        if (tempt.getWidth() != factor * size) {
                            tempt.resizeScale(factor * size, factor * size);
                        } else if (tempt.getHeight() != factor * size) tempt.resizeScale(factor * size, factor * size);
                    }
                }
            }
        }
        columns = Window.getInstance().getWidth() / factor * size;
        rows = Window.getInstance().getHeight() / factor * size;

        this.follow = follow;

    }

    @Override
    public void paint(Graphics g) {
        paintWorld(g);
        paintEntities(g);
    }

    private void paintWorld(Graphics g) {

        if (follow != null){
            x = follow.getLocation().x;
            y = follow.getLocation().y;
        }
        int ycap = Math.min(rows + 1, World.getCurrent().getHeight());
        int xcap = Math.min(columns + 1, World.getCurrent().getWidth());
        int xoffset = Math.max(Math.min(xcap + this.x, World.getCurrent().getWidth()) - xcap,0);
        int yoffset = Math.max(Math.min(ycap + this.y, World.getCurrent().getHeight()) - ycap, 0);

        for (int y = yoffset; y < ycap + yoffset; y++) {
            for (int x = xoffset; x < xcap + xoffset; x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = (Texture) Resources.getResource(World.getCurrent().getTiles()[x][y][layer].getTexture());
                        if(tempt == null) continue;
                        g.drawImage(tempt.getBufferedImage(), (x - this.x) * factor * size, (y - this.y) * factor * size, null);
                    }
                }
            }
        }

    }

    private void paintEntities(Graphics g) {
        for (Entity e : World.getCurrent().getEntities()) {
            Texture tex = ((AnimatedTexture) Resources.getResource(e.getTexture()));
            tex.resizeScale((factor / size) * tex.getWidth(), (factor / size) * tex.getHeight());
            g.drawImage(tex.getImage(), e.getLocation().x * (factor / size), e.getLocation().y * (factor / size), null);
        }
    }
}
