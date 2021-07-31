package io.github.minetrinity.game.load;


import io.github.minetrinity.game.graphics.AnimatedTexture;
import io.github.minetrinity.game.graphics.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public class TextureFactory extends ResourceFactory<Texture> {

    @Override
    public Texture read(File f) {
        Texture t = super.read(f);
        put(f.getName(), t);
        return t;
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
