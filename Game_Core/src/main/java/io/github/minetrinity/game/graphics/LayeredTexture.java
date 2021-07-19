package io.github.minetrinity.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

public class LayeredTexture extends Texture {

    private ArrayList<Image> layers = new ArrayList<>();
    private ArrayList<Point> locations = new ArrayList<>();

    public LayeredTexture(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public LayeredTexture(Image... images) {
        this(images[0].getWidth(null), images[0].getHeight(null));
        this.loaded = true;
        addAll(images);
    }

    public LayeredTexture(Texture... textures) {
        this(textures[0].getWidth(), textures[0].getHeight());
        this.loaded = true;
        addAll(textures);
    }

    @Override
    public void paint(Graphics g) {
        for(int i = 0; i < layers.size(); i++){
            g.drawImage(layers.get(i), locations.get(i).x, locations.get(i).y, null);
        }
    }

    @Override
    public java.awt.Image getImage() {
        return getBufferedImage();
    }

    @Override
    public void setImage(Image img) {
        throw new UnsupportedOperationException("Not a normal Texture");
    }

    @Override
    public BufferedImage getBufferedImage() {
        BufferedImage merged = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = merged.createGraphics();
        paint(g);
        g.dispose();
        return merged;
    }

    @Override
    public void resizeCut(int width, int height, Color fill) {
        super.resizeCut(width, height, fill);
    }

    @Override
    public void resizeScale(int width, int height) {
        super.resizeScale(width, height);
    }


    public Image getImage(int layer) {
        return layers.get(layer);
    }

    public BufferedImage getBufferedImage(int layer){
        return Textures.toBufferedImage(getImage(layer));
    }

    public Point getPoint(int layer){
        return locations.get(layer);
    }

    public void remove(int layer){
        layers.remove(layer);
        locations.remove(layer);
    }

    public void add(Image i) {
        layers.add(i);
        locations.add(new Point(0,0));
    }

    public void put(Image i, Point p){
        layers.add(i);
        locations.add(p);
    }

    public void addAll(Image... images) {
        for (Image i : images) {
            add(i);
        }
    }

    public void addAll(Texture... textures) {
        for (Texture t : textures) {
            add(t.getImage());
        }
    }

    public int getLayerCount() {
        return layers.size();
    }
}
