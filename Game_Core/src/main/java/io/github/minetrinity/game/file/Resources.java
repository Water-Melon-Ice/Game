package io.github.minetrinity.game.file;

import io.github.minetrinity.game.graphics.Textures;
import io.github.minetrinity.game.ingame.world.Tile;
import io.github.minetrinity.game.ingame.world.Tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Resources {

    public static String resPath = "/resources";
    public static String defaultResPath = "/defaultresources";

    static {
        URL jar = Resources.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            File f = new File(jar.toURI());
            resPath = f.getParent() + resPath;
            defaultResPath = f.getParent() + defaultResPath;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<ResourceFactory<?>> resourceFactories = new ArrayList<>();

    public static ArrayList<ResourceFactory<?>> getResourceFactories() {
        return resourceFactories;
    }

    public static void registerResourceFactory(ResourceFactory<?> factory){
        resourceFactories.add(factory);
    }

    public static void unregisterResourceFactory(ResourceFactory<?> factory){
        resourceFactories.remove(factory);
    }

    public static ResourceFactory<?>[] getResourceFactories(String format){
        ArrayList<ResourceFactory<?>> rffs = new ArrayList<>();
        for(ResourceFactory<?> rf : resourceFactories){
            if(rf.isReadable(format)) rffs.add(rf);
        }
        return rffs.toArray(ResourceFactory[]::new);
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

    public static InputStream getInputstream(File f){
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fin;
    }

    public static void processFiles(ArrayList<File> files){

        for(File f : files){
            getResourceFactories(getFileType(f))[0].read(f);
        }
    }

    public static String getFileType(File f){
        return f.getName().substring(f.getName().indexOf(".") + 1);
    }

}
