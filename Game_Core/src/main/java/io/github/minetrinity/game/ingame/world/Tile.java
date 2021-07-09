package io.github.minetrinity.game.ingame.world;

import java.awt.*;
import java.util.HashMap;

public class Tile {

    public static final HashMap<Color, Tile> tiles = new HashMap<>();

    public static Tile from(Color c){
        if(c.getAlpha() == 0) return null;
        return tiles.get(c);
    }

}
