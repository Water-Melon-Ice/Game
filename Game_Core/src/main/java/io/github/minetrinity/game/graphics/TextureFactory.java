package io.github.minetrinity.game.graphics;


import io.github.minetrinity.game.file.ResourceFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public final class TextureFactory extends ResourceFactory<Texture> {

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
        put(f.getName(), (Texture) ResourceFactory.getResourceFactories(f)[0].read(f));
    }

    public static void putAll(File[] files) {
        for (File f : files) {
            put(f);
        }
    }
    public static void release(){
        texturemap.clear();
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


    @Override
    public Texture read(InputStream in, String format) {
        Texture t = null;
        switch (format){
            case "gif":
                t = new AnimatedTexture();
                break;
            case "png":
                t = new Texture();
                break;
        }
        t.read(in);
        return t;
    }

    @Override
    public boolean isReadable(String format) {
        switch (format){
            case "gif":
            case "png":
                return true;
        }
        return false;
    }
}
