package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.file.Resources;

import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class Tiles {

    public static final HashMap<Color, Tile> tiles = new HashMap<>();
    public static final HashMap<Color, String> stringmap = new HashMap<>();

    public static Tile from(Color c){
        if(c.getAlpha() == 0) return null;
        return tiles.get(c);
    }

    public static void fillTileMap(){
        for(Color c : stringmap.keySet()){
            tiles.put(c, new Tile(stringmap.get(c)));
        }
    }

    public static HashMap<Color, String> getColorCodes(File cc){
        try {
            FileInputStream fin = new FileInputStream(cc);
            BufferedReader bufr = new BufferedReader(new InputStreamReader(fin));
            String line;
            while((line = bufr.readLine()) != null){
                String key = line.split("=")[0];
                String value = line.split("=")[1];
                stringmap.put(Color.decode(key), value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringmap;
    }

}
