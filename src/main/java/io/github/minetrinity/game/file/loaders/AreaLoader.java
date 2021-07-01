package io.github.minetrinity.game.file.loaders;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AreaLoader extends Loader<Map<Color, String>>{

    public static final Map<String, Map<Color, String>> map = new HashMap<>();

    String firstline;

    public Map<Color, String> colorcodemap = new HashMap<>();

    public AreaLoader(InputStream in) {
        super(in);
    }

    @Override
    public Map<Color, String> load() {
        try{
            if(!isLoadable()) return null;

            BufferedReader s = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = s.readLine()) != null){
                String[] parts = line.split("=");
                colorcodemap.put(Color.decode(parts[0]), parts[1]);
            }
            map.put(firstline, colorcodemap);
        }catch(IOException e){
            e.printStackTrace();
        }
        return colorcodemap;
    }

    private String getFirstline(){
        BufferedReader s = new BufferedReader(new InputStreamReader(in));
        try {
            if (firstline == null) {
                this.firstline = s.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return firstline;
    }

    @Override
    public boolean isLoadable() {
        if(ImageLoader.getImage(getFirstline()) != null) return true;
        return false;
    }
}
