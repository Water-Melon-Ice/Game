package io.github.minetrinity.game.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Resource {

    private static boolean isIDE = true;
    public static final String ideResPath = "/../../../resources";
    public static final String resPath = "./resources";

    public static File[] allFiles(){
        try {
            Stream<Path> paths = Files.walk(Paths.get(isIDE ? ideResPath : resPath)).filter(p -> p.toFile().isFile());
            ArrayList<File> files = new ArrayList<>();
            paths.forEach(p -> files.add(p.toFile()));
            return files.toArray(File[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
