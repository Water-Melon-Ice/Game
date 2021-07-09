package io.github.minetrinity.game.ingame.world;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.HashMap;

public class Tile {

    public static final HashMap<Color, Tile> tiles = new HashMap<>();

    public static Tile from(Color c){
        if(c.getAlpha() == 0) return null;
        return tiles.get(c);
    }

    protected String path;
    protected BufferedImage image;

    public Tile(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public BufferedImage getImage(){
        return image;
    }

    public void setImage(BufferedImage img){
        this.image = img;
    }
}
