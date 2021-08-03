package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.time.Tickable;

import java.awt.*;

public abstract class Entity implements Tickable {

    public String texture = "Character(L).gif";

    protected Area area;

    protected Rectangle hitbox;

    public Entity(){
        hitbox = new Rectangle(16, 32);
    }

    @Override
    public void tick() {

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
        if(this.area != null) this.area.remove(this);
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
}
