package io.github.minetrinity.game.loaders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

public class ImageLoader extends Loader<Image> {

    private static final ConcurrentHashMap<String, Image> images = new ConcurrentHashMap<>();

    public static Image getImage(String key){
        return images.get(key);
    }

    protected Image img;
    protected String key;

    public ImageLoader(String key, InputStream in) {
        super(in);
        this.key = key;
    }

    public Image load(){
        if(img == null) {
            try {
                img = ImageIO.read(in);
                images.put(key, img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public Image getImg() {
        return img;
    }
}
