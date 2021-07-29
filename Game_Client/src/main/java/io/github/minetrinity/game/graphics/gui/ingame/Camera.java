package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.graphics.Overlay;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.load.TextureFactory;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;

public class Camera extends Overlay {

    private int ticks = 0;
    private int size = 16;
    private int x = 0, y = 0;


    public Camera() {
        setVisible(true);
        for (int y = 0; y < World.getInstance().getCurrent().getHeight(); y++) {
            for (int x = 0; x < World.getInstance().getCurrent().getWidth(); x++) {
                for (int layer = 0; layer < World.getInstance().getCurrent().getLayers(); layer++) {
                    if (World.getInstance().getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = TextureFactory.getByName(World.getInstance().getCurrent().getTiles()[x][y][layer].getTexture());
                        if(tempt.getWidth() != size) tempt.resizeScale(size, size);
                        else if (tempt.getHeight() != size) tempt.resizeScale(size, size);
                    }
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        paintWorld(g);
    }

    public void paintWorld(Graphics g){
        x += 4 * Controls.getX();
        y += 4 * Controls.getY();
        for (int y = 0; y < World.getInstance().getCurrent().getHeight(); y++) {
            for (int x = 0; x < World.getInstance().getCurrent().getWidth(); x++) {
                for (int layer = 0; layer < World.getInstance().getCurrent().getLayers(); layer++) {
                    if (World.getInstance().getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = TextureFactory.getByName(World.getInstance().getCurrent().getTiles()[x][y][layer].getTexture());
                        g.drawImage(tempt.getBufferedImage(), x * size + this.x, y * size + this.y, null);
                    }
                }
            }
        }
    }

}
