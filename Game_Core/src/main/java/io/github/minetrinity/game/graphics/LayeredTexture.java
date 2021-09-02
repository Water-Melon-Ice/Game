package io.github.minetrinity.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LayeredTexture extends Texture {

    private ArrayList<Image> layers = new ArrayList<>();

    public LayeredTexture(Texture... textures) {
        this.width = textures[0].getWidth();
        this.height = textures[0].getHeight();
        this.loaded = true;
        addAll(textures);
    }

    public LayeredTexture() {
        this.loaded = false;
    }

    @Override
    public void paint(Graphics g) {
        for(int i = 0; i < layers.size(); i++){
            g.drawImage(layers.get(i), 0, 0, null);
        }
    }

    @Override
    public BufferedImage getImage() {
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
        return toBufferedImage(getImage(layer));
    }


    public void remove(int layer){
        layers.remove(layer);
    }

    public void add(Image i) {
        put(i, layers.size());
    }


    public void put(Image i, int pos){
        if(width == 0) width = i.getWidth(null);
        if(height == 0) height = i.getHeight(null);
        layers.add(pos, i);
    }

    public void addAll(Image... images) {
        for (Image i : images) {
            add(i);
        }
    }

    public void add(Texture t) {
        add(t.getImage());
    }


    public void put(Texture t, int pos){
        put(t.getImage(), pos);
    }

    public void addAll(Texture... textures) {
        for (Texture t : textures) {
            add(t);
        }
    }

    public int getLayerCount() {
        return layers.size();
    }

    public Color getColor(int x, int y, int layer) {
        return new Color(getBufferedImage(layer).getRGB(x,y), true);
    }
}
