package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.graphics.Texture;

import java.awt.*;
import java.util.HashMap;

public class Tile {

    public static final HashMap<Color, Tile> tiles = new HashMap<>();

    public static Tile from(Color c){
        if(c.getAlpha() == 0) return null;
        return tiles.get(c);
    }

    protected Texture tex;

    public Tile(Texture t){
        this.tex = t;
    }

    public Texture getTexture(){
        return tex;
    }

    public void setTexture(Texture t){
        this.tex = t;
    }
}
