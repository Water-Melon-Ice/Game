package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.file.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Texture extends Resource<Image> implements Paintable{

    private Image img;

    protected int width, height;

    protected boolean loaded = false;
    protected InputStream in;

    public Texture(InputStream in) {
        this.in = in;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(get(), 0,0,null);
    }

    @Override
    public Image get() {
        return img;
    }

    @Override
    public void reload() {
        return;
    }

    @Override
    public boolean isReloadable() {
        return false;
    }

    protected void setImage(Image img) {
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public BufferedImage getBufferedImage() {
        return Textures.toBufferedImage(get());
    }

    public void resizeCut(int width, int height, Color fill) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        g.setColor(fill);
        g.fillRect(0, 0, width, height);
        paint(g);
        g.dispose();
        setImage(img);
    }

    public void moveCut(int x, int y){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        g.translate(x,y);
        paint(g);
        g.dispose();
        setImage(img);
    }

    public void resizeScale(int width, int height) {
        setImage(get().getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT));
    }

    public void read(){
        try {
            setImage(ImageIO.read(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
