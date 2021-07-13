package io.github.minetrinity.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.InvalidKeyException;
import java.util.HashMap;

public class Texture implements Paintable{

    public static Color transparent = new Color(0, 0, 0, 0);

    protected static HashMap<String, Texture> texturemap = new HashMap<>();

    public static Texture get(String key) {
        return texturemap.get(key);
    }

    public static void put(String key, Texture value) throws InvalidKeyException {
        if(texturemap.containsKey(key)) throw new InvalidKeyException("Key already exists!");
        texturemap.put(key, value);
    }

    public static void put(Texture tex) throws InvalidKeyException {
        if(tex.getName() == null) throw new IllegalArgumentException("No key given.");
        put(tex.getName(), tex);
    }

    private Image img;
    private String name;

    protected int width, height;

    public Texture(String name){
        this.name = name;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(getImage(), 0,0,null);
    }

    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public BufferedImage getBufferedImage() {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimg.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        img = bimg;
        return (BufferedImage) img;
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
        setImage(getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }


    public String getName(){
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
