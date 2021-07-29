package io.github.minetrinity.game.ingame.world;

import java.awt.*;
import java.util.HashMap;

public class Tile {

    public static final HashMap<Color, Tile> tiles = new HashMap<>();

    public static Tile from(Color c){
        if(c.getAlpha() != 255) return null;
        return tiles.get(c);
    }

    protected String texture;

    public Tile(String texture){
        this.texture = texture;
    }

    public String getTexture(){
        return texture;
    }

    public void setTexture(String t){
        this.texture = t;
    }
}
