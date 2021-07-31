package io.github.minetrinity.game.load;

import io.github.minetrinity.game.graphics.Texture;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVFactory extends ResourceFactory<ArrayList<String[]>> {


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
                if(line.equalsIgnoreCase("")) continue;
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
