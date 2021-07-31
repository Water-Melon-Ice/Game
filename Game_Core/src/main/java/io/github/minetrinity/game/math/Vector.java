package io.github.minetrinity.game.math;

public class Vector {

    public static final Vector zero = new Vector(AccuratePoint.zero);

    protected AccuratePoint location;
    protected AccuratePoint direction;

    public Vector(AccuratePoint p){
        this.location = AccuratePoint.zero;
        this.direction = p;
    }

    public Vector(AccuratePoint p, AccuratePoint direction){
        this.location = p;
        this.direction = direction;
    }

    public AccuratePoint getLocation() {
        return location;
    }

    public AccuratePoint getDirection() {
        return direction;
    }

    public void move(double factor){
        location.dx += factor * direction.dx;
        location.dy += factor * direction.dy;
    }

}
