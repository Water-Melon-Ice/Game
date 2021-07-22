package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.graphics.LayeredTexture;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.Textures;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.ingame.event.Event;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Area {

    BufferedImage image;
    int ticks = 0;

    public static Area from(LayeredTexture ltex) {
        Area area = new Area(ltex.getWidth(), ltex.getHeight());
        for (int y = 0; y < ltex.getHeight(); y++) {
            for (int x = 0; x < ltex.getWidth(); x++) {
                for (int layer = 0; layer < ltex.getLayerCount(); layer++) {
                    Color c = Textures.getColor(ltex.getBufferedImage(layer), x, y);
                    Tile t = Tiles.from(c);
                    area.setTile(t, x, y, layer);
                }
            }
        }
        return area;
    }

    public BufferedImage toImage(){
        if(image == null || ticks > 5) {
            BufferedImage img = new BufferedImage(getWidth() * 16, getHeight() * 16, BufferedImage.TYPE_INT_ARGB);
            Graphics g = img.createGraphics();
            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    for (int layer = 0; layer < getLayers(); layer++) {
                        if (tiles[x][y][layer] != null) {
                            Texture tempt = Textures.getByName(tiles[x][y][layer].getTexture());
                            g.drawImage(tempt.getBufferedImage(), x * 16, y * 16, null);
                        }
                    }
                }
            }
            g.dispose();
            image = img;
            ticks = 0;
        }else {
            ticks++;
        }
        return image;
    }

    ArrayList<Event> events;
    ArrayList<Entity> entities;
    private Tile[][][] tiles; // x, y, layer
    private ArrayList<Tile> differentTiles = new ArrayList<>();

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
