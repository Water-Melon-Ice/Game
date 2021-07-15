package io.github.minetrinity.game.file;

import io.github.minetrinity.game.graphics.Textures;
import io.github.minetrinity.game.ingame.world.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Resource {

    private static boolean isIDE = true;
    public static final String ideResPath = new File("../defaultresources").getAbsolutePath();
    public static final String resPath = new File("./defaultresources").getAbsolutePath();

    public static ArrayList<File> allFiles(){
        return walk(isIDE ? ideResPath : resPath);
    }

    public static ArrayList<File> allTiles() {
        return walk((isIDE ? ideResPath : resPath) + "/images/tiles/");
    }

    public static ArrayList<BufferedImage> allImages(List<File> files) throws IOException {
        ArrayList<BufferedImage> images = new ArrayList<>();
        for(int i = 0; i < files.size(); i++){
            File f = files.get(i);
            if(f.getName().endsWith(".png")){
                images.add(ImageIO.read(f));
            }
        }
        return images;
    }

    public static BufferedImage getImage(File f){
        if(f.getName().endsWith(".png")){
            try {
                return ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<File> walk(String path){
        ArrayList<File> files = new ArrayList<>();
        try {
            Path pa = Paths.get(path);
            Stream<Path> paths = Files.walk(pa).filter(p -> p.toFile().isFile());
            paths.forEach(p -> files.add(p.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public static void fillTileMap(){
        HashMap<Color, String> stringmap = getColorCodes();
        for(Color c : stringmap.keySet()){
            Tile.tiles.put(c, new Tile(Textures.getByName(stringmap.get(c))));
        }
    }

    public static HashMap<Color, String> getColorCodes(){
        HashMap<Color, String> stringmap = new HashMap<>();
        File cc = new File((isIDE ? ideResPath : resPath) + "/text/colorcodes.area");
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

    public static InputStream getInputstream(File f){
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fin;
    }

}
