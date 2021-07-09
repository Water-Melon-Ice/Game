package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.file.Resource;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AreaToImage {

    public static HashMap<Tile, Image> imageMap = new HashMap<>();

    public static BufferedImage toImage(Area a, int tilesize) {
        if (tilesize == -1) tilesize = 64;
        BufferedImage img = new BufferedImage(a.getWidth() * tilesize, a.getHeight() * tilesize, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        Image temp;
        int twidth, theight;

        for (int x = 0; x < a.getWidth(); x++) {
            for (int y = 0; y < a.getHeight(); y++) {
                for (int l = 0; l < a.getLayers(); l++) {
                    temp = getImage(a.getTile(x, y, l));
                    if(temp == null) continue;
                    twidth = temp.getWidth(null);
                    theight = temp.getHeight(null);
                    if ((temp.getWidth(null) > tilesize || temp.getHeight(null) > tilesize) && tilesize != 0) {
                        return toImage(a, Math.max(twidth, theight));
                    } else {
                        temp = temp.getScaledInstance(tilesize, tilesize, BufferedImage.SCALE_DEFAULT);
                        g.drawImage(temp, x * tilesize, y * tilesize, null);
                    }
                }
            }
        }
        g.dispose();
        return img;
    }

    public static Image getImage(Tile t) {
        if (t == null) return null;
        return imageMap.get(t);
    }

    public static void fillTileImageMap() {
        try {
            ArrayList<File> tiles = Resource.allTiles();
            for (Tile t : Tile.tiles.values()) {
                for (File img : tiles) {
                    if (img.getName().endsWith(t.getPath()))
                        imageMap.put(t, ImageIO.read(img));
                    System.out.println(t);
                    System.out.println(img);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
