package io.github.minetrinity.game.math;

import java.awt.*;

public class Vector {

    public static final Vector zero = new Vector(new Point());

    protected Point location;
    protected Point direction;

    public Vector(){
        this(new Point());
    }

    public Vector(Point p){
        this(new Point(), p);
    }

    public Vector(Point p, Point direction){
        this.location = p;
        this.direction = direction;
    }

    public Point getLocation() {
        return location;
    }

    public Point getDirection() {
        return direction;
    }

    public void move(double factor) {
        location.x += factor * direction.x;
        location.y += factor * direction.y;
    }

}
