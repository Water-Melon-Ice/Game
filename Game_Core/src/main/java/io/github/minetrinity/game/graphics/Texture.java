package io.github.minetrinity.game.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.util.HashMap;

public class Texture implements Paintable{

    private Image img;

    protected int width, height;

    protected boolean loaded = false;
    protected InputStream in;

    public Texture(){}

    public Texture(int width, int height){
        this.width = width;
        this.height = height;
    }

    public Texture(Image i){
        setImage(i);
    }

    public Texture(InputStream in) {
        this.in = in;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(getImage(), 0,0,null);
    }

    public Image getImage() {
        return img;
    }

    protected void setImage(Image img) {
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public BufferedImage getBufferedImage() {
        return Textures.toBufferedImage(getImage());
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
