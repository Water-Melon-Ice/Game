package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.math.Vector;

import java.awt.*;

public class MovingEntity extends LivingEntity {

    protected Vector entityVector;

    protected double airDrag = 0.9;
    protected double maxSpeed = 20;

    public MovingEntity(){
        super();
        entityVector = new Vector(new Point(1,1));
    }

    @Override
    public void tick() {
        super.tick();
        move();

    }

    public void move(){

    }

    public void moveTowardsPoint(Point p){

    }

    @Override
    public void setLocation(Point location) {
        super.setLocation(location);
    }
}
