package io.github.minetrinity.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LayeredImage {

    public static Color transparent = new Color(0, 0, 0, 0);

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimg.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimg;
    }

    public static Image resizeFillBlank(Image i, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics g = img.createGraphics();
        g.setColor(transparent);
        g.fillRect(0, 0, width, height);
        g.drawImage(i, 0, 0, null);
        g.dispose();

        return img;
    }


    protected ArrayList<BufferedImage> layers = new ArrayList<>();

    int width, height;

    public LayeredImage() {
    }

    public LayeredImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public LayeredImage(Image... images) {
        this(images[0].getWidth(null), images[0].getHeight(null));
        forceAddAll(images);
    }

    public BufferedImage getLayer(int i) {
        return layers.get(i);
    }

    public void add(Image i) {
        if(!isCompatible(i)) return;
        layers.add(toBufferedImage(i));
    }

    public void forceAdd(Image i){
        add(makeCompatible(i));
    }

    public void addAll(Image... images) {
        for (int i = 0; i < images.length; i++) {
            add(images[i]);
        }
    }

    public void forceAddAll(Image... images){
        for (int i = 0; i < images.length; i++) {
            forceAdd(images[i]);
        }
    }

    public boolean isCompatible(Image i) {
        if (i.getWidth(null) != width && width != 0) {
            return false;
        }
        if (i.getHeight(null) != height && height != 0) {
            return false;
        }
        makeCompatible(i);
        return true;
    }

    public Image makeCompatible(Image i) {
        if (width == 0 && height == 0 && layers.size() == 0) {
            width = i.getWidth(null);
            height = i.getHeight(null);
            return i;
        }
        return resizeFillBlank(i, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLayers(){
        return layers.size();
    }
}
