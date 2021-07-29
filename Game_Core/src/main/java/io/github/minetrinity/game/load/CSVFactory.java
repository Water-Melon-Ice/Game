package io.github.minetrinity.game.load;

import io.github.minetrinity.game.graphics.Texture;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVFactory extends ResourceFactory<ArrayList<String[]>> {


    private static HashMap<String, ArrayList<String[]>> csvmap = new HashMap<>();

    public static ArrayList<String[]> getByName(String key) {
        return csvmap.get(key);
    }

    public static void put(String key, ArrayList<String[]> value) throws RuntimeException {
        csvmap.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static void put(File f) {
        put(f.getName(), (ArrayList<String[]>) ResourceFactory.getResourceFactories(f)[0].read(f));
    }

    public static void putAll(File[] files) {
        for (File f : files) {
            put(f);
        }
    }

    public static void release(){
        csvmap.clear();
    }

    @Override
    public ArrayList<String[]> read(File f) {
        ArrayList<String[]> t = super.read(f);
        put(f.getName(), t);
        return t;
    }

    @Override
    public ArrayList<String[]> read(InputStream in, String format) {
        String delimiter = ",";
        try {
            BufferedReader bufr = new BufferedReader(new InputStreamReader(in));
            ArrayList<String[]> linelist = new ArrayList<>();
            String line;
            while ((line = bufr.readLine()) != null) {
                String[] sa = line.split(delimiter);
                linelist.add(sa);
            }
            return linelist;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isReadable(String format) {
        return format.equals("csv");
    }
}
