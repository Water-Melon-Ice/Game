package io.github.minetrinity.game.io;

import java.io.*;
import java.util.ArrayList;

public class CSVIO extends ResourceIO<ArrayList<String[]>> {

    protected String delimiter = ",";
    protected String comment = "#";

    @Override
    public ArrayList<String[]> read(InputStream in) {

        try {
            BufferedReader bufr = new BufferedReader(new InputStreamReader(in));
            ArrayList<String[]> linelist = new ArrayList<>();
            String line;
            while ((line = bufr.readLine()) != null) {
                if(line.equalsIgnoreCase("")) continue;
                if(line.startsWith(comment)) continue;
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
    public void write(OutputStream out, ArrayList<String[]> obj) {

    }


    @Override
    public boolean isReadable(String format) {
        return format.equals("csv");
    }

    @Override
    public boolean isWriteable() {
        return true;
    }
}
