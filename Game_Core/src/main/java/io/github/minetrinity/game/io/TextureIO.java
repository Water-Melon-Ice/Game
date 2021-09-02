package io.github.minetrinity.game.io;


import io.github.minetrinity.game.graphics.Texture;

import javax.imageio.ImageIO;
import java.io.*;

public class TextureIO extends ResourceIO<Texture> {

    @Override
    public Texture read(InputStream in) {
        Texture t = new Texture();
        try {
            t.setImage(ImageIO.read(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void write(OutputStream out, Texture obj) {

    }

    @Override
    public boolean isReadable(String format) {
        switch (format){
            case "png":
            case "jpg":
                return true;
        }
        return false;
    }

    @Override
    public boolean isWriteable() {
        return false;
    }
}
