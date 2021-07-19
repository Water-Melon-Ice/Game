package io.github.minetrinity.game.graphics;


import io.github.minetrinity.game.file.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public final class Textures {

    public static Color transparent = new Color(0, 0, 0, 0);

    private static HashMap<String, Texture> texturemap = new HashMap<>();

    public static Texture getByName(String key) {
        return texturemap.get(key);
    }

    public static void put(String key, Texture value) throws RuntimeException {
        if (texturemap.containsKey(key)) throw new RuntimeException("Key already exists!");
        texturemap.put(key, value);
    }

    public static void put(File f) {
        put(f.getName(), toTexture(f));
    }

    public static void putAll(File[] files) {
        for (File f : files) {
            put(f);
        }
    }

    public static BufferedImage toBufferedImage(Image i) {
        if (i instanceof BufferedImage) {
            return (BufferedImage) i;
        }
        BufferedImage bimg = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimg.createGraphics();
        bGr.drawImage(i, 0, 0, null);
        bGr.dispose();
        return bimg;
    }

    public static InputStream getInputstream(File f) {
        return Resources.getInputstream(f);
    }

    public static Texture toTexture(File f) {
        Texture t = null;
        switch (f.getName().substring(f.getName().lastIndexOf(".")+1)){
            case "gif":
                t = new AnimatedTexture(getInputstream(f));
                break;
            case "png":
                t = new Texture(getInputstream(f));
                break;
        }
        t.read();
        return t;
    }






}
