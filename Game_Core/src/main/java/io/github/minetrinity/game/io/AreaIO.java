package io.github.minetrinity.game.io;

import io.github.minetrinity.game.graphics.LayeredTexture;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.Tile;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

//TODO: what to do with this garbage???
public class AreaIO {

    private static CSVIO csv = new CSVIO();

    public static final HashMap<Color, Tile> tiles = new HashMap<>();

    public static Area create(String resPath, String name) {
        String areaRoot = resPath + Resources.worldsPath + "/" + name;

        File[] areafiles = Resources.walk(areaRoot, Integer.MAX_VALUE, true);

        TextureIO texfac = new TextureIO();
        for (File f : areafiles) {
            if (texfac.isReadable(Resources.getFileFormat(f))) {
                texfac.put(f.getName(), Resources.getInputstream(f));
            }

        }
        for (File f : areafiles) {
            if (csv.isReadable(Resources.getFileFormat(f))) {
                csv.put(f.getName(), Resources.getInputstream(f));
            }

        }
        ArrayList<String[]> layerlist = csv.getByName("layers.csv");

        LayeredTexture lt = new LayeredTexture();
        for (String[] sa : layerlist) {
            lt.add(texfac.getByName(sa[0]));
        }
        fillTileMap();
        return toArea(lt);
    }

    //TODO: actually DO return the hashmap from this method (u know what to do anyway...)
    public static void fillTileMap() {
        ArrayList<String[]> cclist = csv.getByName("colorcodes.csv");
        for (String[] sa : cclist) {
            Tile t = new Tile(sa[1]);
            if (sa.length > 2) {
                t.setWalkable(Boolean.parseBoolean(sa[2]));
                System.out.println(sa[1]);
            }
            tiles.put(Color.decode("#" + sa[0]), t);
        }
    }

    public static Tile toTile(Color c) {
        if (c.getAlpha() != 255) return null;
        return tiles.get(c);
    }

    //TODO: this is da lag machine (not anymore, because something else is worse :D)
    public static Area toArea(LayeredTexture ltex) {
        Area area = new Area(ltex.getWidth(), ltex.getHeight(), ltex.getLayerCount());
        for (int y = 0; y < ltex.getHeight(); y++) {
            for (int x = 0; x < ltex.getWidth(); x++) {
                for (int layer = 0; layer < ltex.getLayerCount(); layer++) {
                    Color c = ltex.getColor(x, y, layer);
                    Tile t = toTile(c);
                    area.setTile(t, x, y, layer);
                }
            }
        }
        return area;
    }

}
