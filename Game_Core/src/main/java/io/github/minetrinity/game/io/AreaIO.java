package io.github.minetrinity.game.io;

import io.github.minetrinity.game.graphics.LayeredTexture;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.Tile;
import io.github.minetrinity.game.load.CSVFactory;
import io.github.minetrinity.game.load.ResourceFactory;
import io.github.minetrinity.game.load.Resources;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

//TODO: what to do with this???
public class AreaIO {

    private static CSVFactory csv = new CSVFactory();

    public static final HashMap<Color, Tile> tiles = new HashMap<>();

    public static Area create(String resPath, String name){
        String areaRoot = resPath + Resources.worldsPath + "/" + name;

        ArrayList<File> areafiles = Resources.walk(areaRoot, Integer.MAX_VALUE, true);
        Resources.processAllFiles(areafiles);

        csv.putAll(areafiles.toArray(File[]::new));
        ArrayList<String[]> layerlist = csv.getByName("layers.csv");

        LayeredTexture lt = new LayeredTexture();
        for (String[] sa : layerlist){
            lt.add((Texture) ResourceFactory.getResourceFactories("png")[0].getByName(sa[0]));
        }
        fillTileMap();
        return toArea(lt);
    }

    //TODO: actually DO return the hashmap from this method (u know what to do anyway...)
    public static void fillTileMap(){
        ArrayList<String[]> cclist = csv.getByName("colorcodes.csv");
        for (String[] sa : cclist){
            tiles.put(Color.decode("#" + sa[0]), new Tile(sa[1]));
        }
    }

    public static Tile toTile(Color c){
        if(c.getAlpha() != 255) return null;
        return tiles.get(c);
    }

    public static Area toArea(LayeredTexture ltex) {
        Area area = new Area(ltex.getWidth(), ltex.getHeight());
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
