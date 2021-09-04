package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.graphics.AnimatedTexture;
import io.github.minetrinity.game.graphics.Overlay;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.io.Resources;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;

public class Camera extends Overlay {

    public int x = 0;
    public int y = 0;

    private int size = 128;
    private int minsize = 16;
    private int zoomfactor;

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
                        if (tempt.getWidth() != size) {
                            tempt.resizeScale(size, size);
                        } else if (tempt.getHeight() != size) tempt.resizeScale(size, size);
                    }
                }
            }
        }
        columns = Window.getInstance().getWidth() / size;
        rows = Window.getInstance().getHeight() / size;
        zoomfactor = ((size / minsize) - 1);

        this.follow = follow;

    }

    @Override
    public void paint(Graphics g) {
        paintWorld(g);
        paintEntities(g);
        drawFPS(g);
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
                        g.drawImage(tempt.getBufferedImage(), (x - this.x) * size, (y - this.y) * size, null);
                    }
                }
            }
        }

    }

    private void paintEntities(Graphics g) {
        for (Entity e : World.getCurrent().getEntities()) {
            Texture tex = ((AnimatedTexture) Resources.getResource(e.getTexture()));
            tex.resizeScale((size / minsize) * tex.getWidth(), (size / minsize) * tex.getHeight());
            g.drawImage(tex.getImage(), e.getLocation().x * (size / minsize), e.getLocation().y * (size / minsize), null);
        }
    }


    private void drawFPS(Graphics g) {
        g.setColor(Color.black);
        g.drawString("" + Client.getInstance().getActualticks(), 10, 10);
    }
}
