package io.github.minetrinity.game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Texture implements Paintable{

    public static Color transparent = new Color(0, 0, 0, 0);

    public static BufferedImage toBufferedImage(Image i) {
        if (i instanceof BufferedImage) {
            return (BufferedImage) i;
        }
        BufferedImage bimg = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimg.createGraphics();
        bGr.drawImage(i, 0, 0, null);
        bGr.dispose();
        return bimg;
    }

    private static int[][] toARGBIntArray(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[width][height];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[col][row] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[col][row] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

    private Image img;

    protected int width, height;
    protected int baseWidth, baseHeight;

    protected boolean loaded = false;

    public Texture() {
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
        Image img = getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        setImage(img);
    }

    public Image getImageFillNonOpaque(Color c){
        BufferedImage img = getBufferedImage();
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int crgba = c.getRGB();
        int[][] argbIntArray = toARGBIntArray(img);
        for(int x = 0; x < argbIntArray.length; x++){
            for(int y = 0; y < argbIntArray[0].length; y++){
                int argb = argbIntArray[x][y];
                int a = (argb & 0xff000000) >>> 24;
                if(a != 255){
                    out.setRGB(x,y, 0x00000000);
                }else{
                    out.setRGB(x,y,crgba);
                }
            }
        }
        return out;
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
        setSize(img);
    }

    public BufferedImage getBufferedImage() {
        return toBufferedImage(getImage());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBaseWidth() {
        return baseWidth;
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    protected void setSize(Image img){
        if(baseWidth == 0 || baseHeight == 0){
            baseWidth = width;
            baseHeight = height;
        }
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    protected double getRatio(){
        return (double) width / height;
    }

    public Color getColor(int x, int y){
        Color c = new Color(getBufferedImage().getRGB(x,y), true);
        return c;
    }
}
