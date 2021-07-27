package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.file.ResourceFactory;

import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class TileFactory extends ResourceFactory<Tile> {

    public static Tile from(Color c){
        if(c.getAlpha() != 255) return null;
        return ResourceFactory.getResourceFactories("cc")[0].re;
    }


    @Override
    public Tile read(InputStream in, String format) {
        String delimiter = ",";
        HashMap<Color, String> map = new HashMap<>();
        try {
            BufferedReader bufr = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = bufr.readLine()) != null) {
                String key = line.split(delimiter)[0];
                String value = line.split(delimiter)[1];
                map.put(Color.decode(key), value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return
    }

    @Override
    public boolean isReadable(String format) {
        return format.equals("cc");
    }
}
