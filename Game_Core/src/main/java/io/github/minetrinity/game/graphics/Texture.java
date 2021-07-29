package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.load.TextureFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Texture implements Paintable{

    private Image img;

    protected int width, height;

    protected boolean loaded = false;

    public Texture() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(getImage(), 0,0,null);
    }

    public Image getImage() {
        return img;
    }

    public void read(InputStream in) {
        try {
            setImage(ImageIO.read(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setImage(Image img) {
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public BufferedImage getBufferedImage() {
        return TextureFactory.toBufferedImage(getImage());
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
        setImage(getImage().getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor(int x, int y){
        Color c = new Color(getBufferedImage().getRGB(x,y), true);
        return c;
    }
}
