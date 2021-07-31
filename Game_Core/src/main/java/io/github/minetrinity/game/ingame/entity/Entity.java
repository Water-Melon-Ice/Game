package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.math.AccuratePoint;
import io.github.minetrinity.game.time.Tickable;

public abstract class Entity implements Tickable {

    public String texture = "Character(L).gif";

    protected Area area;
    protected AccuratePoint location;

    public Entity(){
        location = AccuratePoint.zero;
    }

    @Override
    public void tick() {

    }

    public AccuratePoint getLocation() {
        return location;
    }

    public void setLocation(AccuratePoint location) {
        this.location = location;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        if(this.area != null) this.area.remove(this);
        this.area = area;
        if(!area.getEntities().contains(this)) area.add(this);
    }
}
