package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.graphics.LayeredTexture;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.ingame.event.Event;

import java.awt.*;
import java.util.ArrayList;

public class Area {

    public static Area from(LayeredTexture ltex) {
        Area area = new Area(ltex.getWidth(), ltex.getHeight());
        for (int y = 0; y < ltex.getHeight(); y++) {
            for (int x = 0; x < ltex.getWidth(); x++) {
                for (int layer = 0; layer < ltex.getLayerCount(); layer++) {
                    Color c = new Color(ltex.getBufferedImage(layer).getRGB(x, y));
                    Tile t = Tile.from(c);
                    area.setTile(t, x, y, layer);
                }
            }
        }
        return area;
    }

    ArrayList<Event> events;
    ArrayList<Entity> entities;
    private Tile[][][] tiles; // x, y, layer

    protected final int layerstack = 5;

    public Area(int width, int height) {
        tiles = new Tile[width][height][layerstack];
    }

    public void setTile(Tile tile, int x, int y, int layer) {
        if (layer > layerstack - 1) return;
        this.tiles[x][y][layer] = tile;
    }

    public Tile[][][] getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y, int layer) {
        return getTiles()[x][y][layer];
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public int getLayers() {
        return tiles[0][0].length;
    }
}
