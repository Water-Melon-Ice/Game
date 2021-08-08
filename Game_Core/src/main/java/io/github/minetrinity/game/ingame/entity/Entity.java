package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.time.Tickable;

import java.awt.*;

public abstract class Entity implements Tickable {

    protected String texture = "";

    private Area area;

    protected Rectangle hitbox;

    public Entity(){
        hitbox = new Rectangle(16, 32);
    }

    @Override
    public void tick() {

    }

    public final void kill(){
        getArea().remove(this);
    }

    public boolean isInHitbox(Point p){
        return hitbox.contains(p.x, p.y);
    }

    public boolean isInHitbox(Entity e){
        return hitbox.contains(e.getHitbox());
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
        if(!area.getEntities().contains(this)) area.add(this);
    }

    public Point getLocation() {
        return hitbox.getLocation();
    }

    public void setLocation(Point location) {
        hitbox.setLocation(location);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public String getTexture() {
        return texture;
    }

    public void onSpawn(){

    }

    public void onDeath(){

    }
}
