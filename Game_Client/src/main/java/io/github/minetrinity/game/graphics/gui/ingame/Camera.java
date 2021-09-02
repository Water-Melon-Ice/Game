package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.graphics.AnimatedTexture;
import io.github.minetrinity.game.graphics.Overlay;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.io.Resources;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;

public class Camera extends Overlay {

    private int ticks = 0;
    private int size = 16;
    private int minsize = 16;
    private int x = 0, y = 0;


    public Camera() {
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
    }

    @Override
    public void paint(Graphics g) {
        ticks++;
        paintWorld(g);
        paintEntities(g);
        drawFPS(g);
    }

    private void paintWorld(Graphics g) {
        for (int y = 0; y < World.getCurrent().getHeight(); y++) {
            for (int x = 0; x < World.getCurrent().getWidth(); x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = (Texture) Resources.getResource(World.getCurrent().getTiles()[x][y][layer].getTexture());
                        g.drawImage(tempt.getBufferedImage(), x * size + this.x, y * size + this.y, null);
                    }
                }
            }
        }

    }

    private void paintEntities(Graphics g){
        for(Entity e : World.getCurrent().getEntities()){
            Texture tex = ((AnimatedTexture) Resources.getResource(e.getTexture()));
            tex.resizeScale((size/minsize) * tex.getWidth(), (size/minsize) * tex.getHeight());
            g.drawImage(tex.getImage(),  e.getLocation().x * (size/minsize),  e.getLocation().y * (size / minsize) , null);
        }
    }


    private void drawFPS(Graphics g) {
        g.setColor(Color.black);
        g.drawString("" + Client.getInstance().getActualticks(), 10,10);
    }
}
