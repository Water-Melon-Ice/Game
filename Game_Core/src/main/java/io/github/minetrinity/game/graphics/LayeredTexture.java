package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

public class LayeredTexture extends Texture {

    //TODO: keep like this or change to 2 different array lists (one Texture other Point)?
    private ArrayList<Texture> layers = new ArrayList<>();
    private ArrayList<Point> locations = new ArrayList<>();

    public LayeredTexture(String name, int width, int height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    public LayeredTexture(int width, int height) {
        this(null, width, height);
    }

    public LayeredTexture(Texture... textures) {
        this(textures[0].getWidth(), textures[0].getHeight());
        addAll(textures);
    }

    @Override
    public void paint(Graphics g) {
        for (Texture t : layers) {
            t.paint(g);
        }
    }

    @Override
    public Image getImage() {
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


    public Texture get(int layer) {
        return layers.get(layer);
    }

    public Point getPoint(int layer){
        return locations.get(layer);
    }

    public void remove(int layer){
        layers.remove(layer);
        locations.remove(layer);
    }

    public void add(Texture t) {
        layers.add(t);
        locations.add(new Point(0,0));
    }

    public void put(Texture t, Point p){
        layers.add(t);
        locations.add(p);
    }

    public void addAll(Texture... textures) {
        for (Texture tex : textures) {
            add(tex);
        }
    }

    public int getLayers() {
        return layers.size();
    }
}
