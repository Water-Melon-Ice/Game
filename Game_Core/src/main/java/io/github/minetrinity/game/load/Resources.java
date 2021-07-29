package io.github.minetrinity.game.load;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Resources {

    public static String resPath = "/resources";
    public static String defaultResPath = "/defaultresources";
    public static String worldsPath = "/worlds";
    public static String globalPath = "/global";

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

    static {
        ResourceFactory.registerResourceFactory(new TextureFactory());
        ResourceFactory.registerResourceFactory(new CSVFactory());
    }

    public static ArrayList<File> walk(String path, int depth, boolean onlyFiles) {
        ArrayList<File> files = new ArrayList<>();
        try {
            Path pa = Paths.get(path);
            Stream<Path> paths = Files.walk(pa, depth);
                    if(onlyFiles) paths = paths.filter(p -> p.toFile().isFile());
            paths.forEach(p -> files.add(p.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public static ArrayList<File> walk(String path, String extension, int depth, boolean onlyFiles) {
        ArrayList<File> files = new ArrayList<>();
        for(File f : walk(path, depth, onlyFiles)){
            if(getFileType(f).equalsIgnoreCase(extension)) files.add(f);
        }
        return files;
    }

    //TODO: return results
    public static void processAllFiles(ArrayList<File> files){
        System.out.println(ResourceFactory.getResourceFactories().size());
        for( File f : files){
            ResourceFactory.getResourceFactories(f)[0].read(f);
        }
    }

    public static InputStream getInputstream(File f) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fin;
    }

    public static String getFileType(File f) {
        return f.getName().substring(f.getName().lastIndexOf(".") + 1);
    }


}
