package io.github.minetrinity.game.ingame.entity;

import java.awt.*;

public class MovingEntity extends LivingEntity {

    protected int speed = 10;
    protected double direction = 0.0;

    protected double airDrag = 0.9;
    protected double maxSpeed = 2000;

    public MovingEntity(){
        super();
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
