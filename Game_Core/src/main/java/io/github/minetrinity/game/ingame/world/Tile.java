package io.github.minetrinity.game.ingame.world;

import java.util.HashMap;

public class Tile {

    public static final HashMap<String, Tile> tiles = new HashMap<>();

    public static Tile getTile(String name){
        if(tiles.containsKey(name)){
            return tiles.get(name);
        }else {
            Tile t = new Tile(name);
            tiles.put(name, t);
            return t;
        }
    }



    protected String texture;

    private Tile(String texture){
        this.texture = texture;
    }

    public String getTexture(){
        return texture;
    }

    public void setTexture(String t){
        this.texture = t;
    }
}
