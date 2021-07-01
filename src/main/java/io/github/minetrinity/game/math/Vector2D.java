package io.github.minetrinity.game.math;

public class Vector2D{

    public static final Vector2D zero = new Vector2D(AccuratePoint.zero);

    protected AccuratePoint location;
    protected AccuratePoint direction;

    public Vector2D(AccuratePoint p){
        location = p;
    }

    public Vector2D(AccuratePoint p, AccuratePoint direction){
        location = p;
        this.direction = direction;
    }

    public AccuratePoint getLocation() {
        return location;
    }

    public AccuratePoint getDirection() {
        return direction;
    }
}
