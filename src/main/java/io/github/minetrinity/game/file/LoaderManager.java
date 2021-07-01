package io.github.minetrinity.game.concurrent;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JarLoader {

    private static final ConcurrentHashMap<String, Image> images = new ConcurrentHashMap<>();

    private static String assetlocation = "io.github.minetrinity.game";
    private static String imglocation = assetlocation + "/images/tiles";

    private static final URI jarlocation;
    static{
        URI jarloc = null;
        try {
            jarloc = JarLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        jarlocation = jarloc;
    }

    public static void loadAll() {
        Path[] paths = getResourceLocations(imglocation);
        load(relativize(paths[1].toUri(), jarlocation));
    }

    public static void load(URI uri) {
        load("/" + uri.toString());
    }

    public static void load(String location) {
        switch (location.substring(location.lastIndexOf('.'))) {
            case ".png":
                loadimage(JarLoader.class.getClassLoader().getResourceAsStream(location));
                break;
        }

    }

    public static void loadimage(InputStream s) {
    }

    public static Path[] getResourceLocations(String location) {
        URI uri = null;
        try {
            uri = JarLoader.class.getClassLoader().getResource(location).toURI();
            Path myPath;
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                myPath = fileSystem.getPath(location);
            } else {
                myPath = Paths.get(uri);
            }
            Stream<Path> walk = Files.walk(myPath, 1);
            walk = walk.skip(1);
            List<Path> l = walk.collect(Collectors.toList());
            return l.toArray(Path[]::new);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URI relativize(URI file, URI root) {
        return root.relativize(file);
    }


}
