package io.github.minetrinity.game.io;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public final class Resources {

    public static String resPath = "/resources";
    public static String defaultResPath = "/defaultresources";
    public static String worldsPath = "/worlds";
    public static String globalPath = "/global";
    public static String imagepath = globalPath + "/images";
    public static String menupath = imagepath + "/gui";

    private static final HashMap<String, Object> resourceMap = new HashMap<>();
    private static final ArrayList<ResourceIO<?>> resourceFactories = new ArrayList<>();

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
        registerDefaultFactories();
    }

    public static void registerDefaultFactories() {
        registerResourceFactory(new TextureIO());
        registerResourceFactory(new GIFIO());
        registerResourceFactory(new CSVIO());
    }


    public static File[] walk(String path, int depth, boolean onlyFiles) {
        ArrayList<File> files = new ArrayList<>();
        try {
            Path pa = Path.of(path);
            Stream<Path> paths = Files.walk(pa, depth);
            if (onlyFiles) paths = paths.filter(p -> p.toFile().isFile());
            paths.forEach(p -> files.add(p.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files.toArray(File[]::new);
    }

    public static File[] walk(String path, String extension, int depth, boolean onlyFiles) {
        ArrayList<File> files = new ArrayList<>();
        for (File f : walk(path, depth, onlyFiles)) {
            if (getFileFormat(f).equalsIgnoreCase(extension)) files.add(f);
        }
        return files.toArray(File[]::new);
    }

    public static File[] walk(String path) {
        return walk(path, Integer.MAX_VALUE, true);
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

    public static String getFileFormat(File f) {
        return f.getName().substring(f.getName().lastIndexOf(".") + 1);
    }

    public static Object getResource(String key) {
        return resourceMap.get(key);
    }

    public static void loadAllFiles(File[] files) {
        for (File f : files) {
            loadFile(f);
        }
    }

    public static void loadFile(File f) {
        resourceMap.put(f.getName(), getResourceFactories(f)[0].read(getInputstream(f)));
    }

    public static void registerResourceFactory(ResourceIO<?> factory) {
        resourceFactories.add(factory);
    }

    public static void unregisterResourceFactory(ResourceIO<?> factory) {
        resourceFactories.remove(factory);
    }

    public static ArrayList<ResourceIO<?>> getResourceFactories() {
        return resourceFactories;
    }

    public static ResourceIO<?>[] getResourceFactories(String format) {
        ArrayList<ResourceIO<?>> rffs = new ArrayList<>();
        for (ResourceIO<?> rf : resourceFactories) {
            if (rf.isReadable(format)) rffs.add(rf);
        }
        return rffs.toArray(ResourceIO[]::new);
    }

    public static ResourceIO<?>[] getResourceFactories(File f) {
        return getResourceFactories(Resources.getFileFormat(f));
    }


}
