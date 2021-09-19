package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.graphics.LayeredTexture;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.ingame.entity.LivingEntity;
import io.github.minetrinity.game.time.Tickable;

import java.awt.*;
import java.util.ArrayList;

public class Area implements Tickable {

    private ArrayList<Entity> entities = new ArrayList<>();
    private Tile[][][] tiles; // x, y, layer


    public Area(int width, int height, int layers) {
        tiles = new Tile[width][height][layers];
    }

    public void setTile(Tile tile, int x, int y, int layer) {
        this.tiles[x][y][layer] = tile;
    }

    public Tile[][][] getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y, int layer) {
        return getTiles()[x][y][layer];
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public int getLayers(){
        return tiles[0][0].length;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void add(Entity e){
        entities.add(e);
        if(e.getArea() != this) e.setArea(this);
    }

    @Override
    public void tick() {
        for(int i = entities.size() - 1; i >= 0; i--){
            entities.get(i).tick();
        }
    }

    public void remove(Entity e){
        entities.remove(e);
    }

    public boolean isWalkable(int x, int y){
        if(x < 0) return false;
        if(y < 0) return false;
        if(x >= getWidth()) return false;
        if(y >= getHeight()) return false;
        for(int i = 0; i < getLayers(); i++){
            Tile t = getTile(x,y,i);
            if(t == null) continue;
            if(!t.isWalkable()) return false;
        }
        return true;
    }
}
