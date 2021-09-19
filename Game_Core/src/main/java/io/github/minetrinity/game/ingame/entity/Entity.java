package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.time.Tickable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Entity implements Tickable {

    protected String texture = "MissingTile.png";

    private Area area;

    protected double x = 0;
    protected double y = 0;
    protected double width = 1;
    protected double height = 2;

    public Entity() {

    }

    @Override
    public void tick() {

    }

    public final void kill() {
        getArea().remove(this);
    }

    public boolean isInHitbox(double x, double y) {
        return false;
    }

    public boolean isInHitbox(Entity e) {
        return false;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
        if (!area.getEntities().contains(this)) area.add(this);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getTexture() {
        return texture;
    }

    public void onSpawn() {

    }

    public void onDeath() {

    }
}
