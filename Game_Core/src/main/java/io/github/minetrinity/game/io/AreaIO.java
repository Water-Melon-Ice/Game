package io.github.minetrinity.game.io;

import io.github.minetrinity.game.graphics.LayeredTexture;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.Tile;
import io.github.minetrinity.game.load.CSVFactory;
import io.github.minetrinity.game.load.Resources;
import io.github.minetrinity.game.load.TextureFactory;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AreaIO {

    public static Area create(String resPath, String name){
        String areaRoot = resPath + Resources.worldsPath + "/" + name;

        ArrayList<File> areafiles = Resources.walk(areaRoot, Integer.MAX_VALUE, true);
        Resources.processAllFiles(areafiles);

        ArrayList<String[]> layerlist = CSVFactory.getByName("layers.csv");
        LayeredTexture lt = new LayeredTexture();
        for (String[] sa : layerlist){
            lt.add(TextureFactory.getByName(sa[0]));
            System.out.println(sa[0]);
        }
        fillTileMap();
        return Area.from(lt);
    }

    public static void fillTileMap(){
        ArrayList<String[]> cclist = CSVFactory.getByName("colorcodes.csv");
        for (String[] sa : cclist){
            Tile.tiles.put(Color.decode("#" + sa[0]), new Tile(sa[1]));
        }
    }

}
