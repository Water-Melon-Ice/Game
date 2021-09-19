package io.github.minetrinity.game.ingame.world;

import java.awt.*;
import java.util.HashMap;

public class Tile {

    protected String texture;
    private boolean isWalkable = false;

    public Tile(String texture){
        this.texture = texture;

    }

    public String getTexture(){
        return texture;
    }

    public void setTexture(String t){
        this.texture = t;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
