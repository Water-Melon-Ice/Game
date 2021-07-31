package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.Game;
import io.github.minetrinity.game.graphics.AnimatedTexture;
import io.github.minetrinity.game.graphics.Overlay;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.load.ResourceFactory;
import io.github.minetrinity.game.load.TextureFactory;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;

public class Camera extends Overlay {

    private int ticks = 0;
    private int size = 16;
    private int x = 0, y = 0;


    public Camera() {
        setVisible(true);
        for (int y = 0; y < World.getCurrent().getHeight(); y++) {
            for (int x = 0; x < World.getCurrent().getWidth(); x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName(World.getCurrent().getTiles()[x][y][layer].getTexture());
                        if (tempt == null) {
                            System.err.println(World.getCurrent().getTiles()[x][y][layer].getTexture());
                        }
                        if (tempt.getWidth() != size) {
                            tempt.resizeScale(size, size);
                        } else if (tempt.getHeight() != size) tempt.resizeScale(size, size);
                    }
                }
            }
        }
        for(Entity e : World.getCurrent().getEntities()){
            Texture tempt = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName(e.texture);
            if (tempt.getWidth() != size) {
                tempt.resizeScale(size, 2 * size);
            } else if (tempt.getHeight() != size) tempt.resizeScale(size, 2 * size);
        }

    }

    @Override
    public void paint(Graphics g) {
        paintWorld(g);
    }

    public void paintWorld(Graphics g) {
        for (int y = 0; y < World.getCurrent().getHeight(); y++) {
            for (int x = 0; x < World.getCurrent().getWidth(); x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName(World.getCurrent().getTiles()[x][y][layer].getTexture());
                        g.drawImage(tempt.getBufferedImage(), x * size + this.x, y * size + this.y, null);
                    }
                }
            }
        }
        for(Entity e : World.getCurrent().getEntities()){
            g.drawImage(((AnimatedTexture) ResourceFactory.getResourceFactories("png")[0].getByName(e.texture)).getImage(), (int) e.getLocation().dx, (int) e.getLocation().dy, null);
        }
        g.setColor(Color.black);
        g.drawString("" + Client.getInstance().getActualticks(), 10,10);
    }

}
