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
import java.util.ArrayList;

public class Camera extends GOverlay {

    public double x = 0;
    public double y = 0;

    private final int factor = 1;
    private final int size = 16;

    private final int rows;
    private final int columns;

    private Entity follow;
    Texture mt = (Texture) Resources.getResource("MissingTile.png");

    public Camera(Entity follow) {

        ArrayList<String> missingTiles = new ArrayList<>();
        mt.resizeScale(factor * size, factor * size);

        for (int y = 0; y < World.getCurrent().getHeight(); y++) {
            for (int x = 0; x < World.getCurrent().getWidth(); x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        String tile = World.getCurrent().getTiles()[x][y][layer].getTexture();
                        Texture tempt = (Texture) Resources.getResource(tile);
                        if (tempt == null) {
                            if (!missingTiles.contains(tile)) {
                                System.err.println(World.getCurrent().getTiles()[x][y][layer].getTexture());
                                missingTiles.add(tile);
                            }
                            continue;
                        }
                        if (tempt.getWidth() != factor * size) {
                            tempt.resizeScale(factor * size, factor * size);
                        } else if (tempt.getHeight() != factor * size)
                            tempt.resizeScale(factor * size, factor * size);
                    }
                }
            }
        }

        columns = (int) Math.ceil(Window.getInstance().getWidth() / (factor * size * 1.0));
        rows = (int) Math.ceil(Window.getInstance().getHeight() / (factor * size * 1.0));

        this.follow = follow;

    }

    @Override
    public void paint(Graphics g) {

        if (follow != null) {
            x = follow.getX() - columns / 2.0;
            y = follow.getY() - rows / 2.0;
        }

        paintWorld(g);
        paintEntities(g);
    }

    private void paintWorld(Graphics g) {

        int xoffset = (int) Math.max(Math.min(columns + this.x, World.getCurrent().getWidth()) - columns, 0);
        int yoffset = (int) Math.max(Math.min(rows + this.y, World.getCurrent().getHeight()) - rows, 0);


        for (int y = yoffset; y < rows + yoffset; y++) {
            for (int x = xoffset; x < columns + xoffset; x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = (Texture) Resources.getResource(World.getCurrent().getTiles()[x][y][layer].getTexture());
                        if (tempt == null) tempt = mt;
                        g.drawImage(tempt.getBufferedImage(), (int) ( (x - this.x) * factor * size), (int) ((y - this.y) * factor * size), null);
                    }
                }
            }
        }

    }

    private void paintEntities(Graphics g) {
        for (Entity e : World.getCurrent().getEntities()) {
            Texture tempt = (Texture) Resources.getResource(e.getTexture());
            if(tempt == null) tempt = mt;
            if (tempt.getWidth() != factor * tempt.getBaseWidth() || tempt.getHeight() != factor * tempt.getBaseHeight()) {
                tempt.resizeScale(factor * tempt.getBaseWidth(), factor * tempt.getBaseHeight());
            }

            g.drawImage(tempt.getImage(), (int) ((e.getX() - this.x) * factor * size), (int) ((e.getY() - this.y) * size * factor), null);

        }
    }
}
