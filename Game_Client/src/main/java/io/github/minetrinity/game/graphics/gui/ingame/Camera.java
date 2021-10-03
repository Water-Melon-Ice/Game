package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.graphics.components.GOverlay;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.io.Resources;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Camera extends GOverlay {

    public double x = 0;
    public double y = 0;

    private final int factor = 2;
    private final int size = 16;
    private double onePxinTile;

    private final int rows;
    private final int columns;

    private Entity follow;
    Texture mt = (Texture) Resources.getResource("MissingTile.png");

    Texture[][][] mapIT;

    public Camera(Entity follow) {

        ArrayList<String> missingTiles = new ArrayList<>();
        mt.resizeScale(factor * size, factor * size);

        mapIT = new Texture[World.getCurrent().getWidth()][World.getCurrent().getHeight()][World.getCurrent().getLayers()];

        for (int x = 0; x < World.getCurrent().getWidth(); x++) {
            for (int y = 0; y < World.getCurrent().getHeight(); y++) {

                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        String tile = World.getCurrent().getTiles()[x][y][layer].getTexture();
                        Texture tempt = (Texture) Resources.getResource(tile);
                        mapIT[x][y][layer] = tempt;
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

        columns = (int) Math.ceil(Window.getInstance().getWidth() / (factor * size * 1.0)) + 1;
        rows = (int) Math.ceil(Window.getInstance().getHeight() / (factor * size * 1.0)) + 1;

        this.follow = follow;

        onePxinTile = 1.0 / (factor * size);

    }

    @Override
    public void paint(Graphics g) {

        if (follow != null) {
            moveCamera();
            smoothenCooridinates();
        }

        paintWorld(g);
        paintEntities(g);
    }

    public void moveCamera() {
        x = follow.getX() - columns / 2.0;
        y = follow.getY() - rows / 2.0;
    }

    private void smoothenCooridinates() {
        double xpo = x % 1;
        if (xpo / onePxinTile != 0) {
            int c = (int) (xpo / onePxinTile);
            x -= xpo;
            x += c * onePxinTile;
        }
        double ypo = y % 1;
        if ((y % 1) / onePxinTile != 0) {
            int c = (int) (ypo / onePxinTile);
            y -= ypo;
            y += c * onePxinTile;
        }
    }

    private void paintWorld(Graphics g) {

        int xoffset = (int) Math.min(columns + this.x, World.getCurrent().getWidth()) - columns;
        int yoffset = (int) Math.min(rows + this.y, World.getCurrent().getHeight()) - rows;


        for (int y = Math.max(yoffset, 0); y < rows + yoffset; y++) {
            for (int x = Math.max(xoffset, 0); x < columns + xoffset; x++) {
                for (int layer = 0; layer < World.getCurrent().getLayers(); layer++) {
                    if (World.getCurrent().getTiles()[x][y][layer] != null) {
                        Texture tempt = mapIT[x][y][layer];
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
            if (tempt == null) tempt = mt;
            if (tempt.getWidth() != factor * tempt.getBaseWidth() || tempt.getHeight() != factor * tempt.getBaseHeight()) {
                tempt.resizeScale(factor * tempt.getBaseWidth(), factor * tempt.getBaseHeight());
            }

            g.drawImage(tempt.getImage(), (int) ((e.getX() - this.x) * factor * size), (int) ((e.getY() - this.y) * size * factor) - tempt.getHeight(), null);

        }
    }
}
