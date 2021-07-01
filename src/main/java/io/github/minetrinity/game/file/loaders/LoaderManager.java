package io.github.minetrinity.game.file.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoaderManager {

    private static Queue<Loader> queue = new ArrayDeque<>();

    private static final String assetlocation = "io/github/minetrinity/game/";

    private static final URI jarlocation;

    static {
        URI jarloc = null;
        try {
            jarloc = LoaderManager.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        jarlocation = jarloc;
    }

    public static void totalLoad() {
        Path[] paths = getResourceLocations(assetlocation);
        Loader[] loaders = getAllLoaders(paths);
        loadAll(loaders);
    }

    public static Loader getLoader(String location) {
        Loader l = null;
        switch (location.substring(location.lastIndexOf('.'))) {
            case ".png":
                l = new ImageLoader(getFilename(location), getResourceAsStream(location));
                break;
            case ".area":
                l = new AreaLoader(getResourceAsStream(location));
                break;
            default:
                return null;
        }
        return l;
    }

    public static Loader getLoader(URI uri) {
        return getLoader(uri.toString());
    }

    public static void loadAll(Loader[] loaders) {
        queue.addAll(Arrays.asList(loaders));
        while (!queue.isEmpty()) {
            Loader l = queue.poll();
            if (l.isLoadable())
                l.load();
            if (!l.isCorrectlyLoaded()) queue.add(l);
        }

    }

    public static Loader[] getAllLoaders(Path... paths) {
        ArrayList<Loader> loaders = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            Loader l = getLoader(relativize(paths[i].toUri(), jarlocation));
            if (l != null)
                loaders.add(l);
        }
        return loaders.toArray(Loader[]::new);
    }

    public static Path[] getResourceLocations(String location) {
        URI uri = null;
        try {
            uri = LoaderManager.class.getClassLoader().getResource(location).toURI();
            Path myPath;
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                myPath = fileSystem.getPath(location);
            } else {
                myPath = Paths.get(uri);
            }
            Stream<Path> walk = Files.walk(myPath);
            List<Path> l = walk.filter(p -> p.toString()
                    .contains("."))
                    .collect(Collectors.toList());

            return l.toArray(Path[]::new);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFilename(String absolutepath) {
        if (absolutepath.lastIndexOf('/') != -1) {
            return absolutepath.substring(absolutepath.lastIndexOf('/') + 1);
        } else if (absolutepath.lastIndexOf('\\') != -1) {
            return absolutepath.substring(absolutepath.lastIndexOf('\\') + 1);
        } else {
            return null;
        }
    }

    public static URI relativize(URI file, URI root) {
        return root.relativize(file);
    }

    public static InputStream getResourceAsStream(String location) {
        return LoaderManager.class.getClassLoader().getResourceAsStream(location);
    }


}
