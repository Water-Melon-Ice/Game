package io.github.minetrinity.game.ingame.entity;

import java.awt.*;

public class MovingEntity extends LivingEntity {

    protected double speed = 0;
    protected double direction = 0.0;

    protected double maxSpeed = 1;

    public MovingEntity(){
        super();
    }

    @Override
    public void tick() {
        super.tick();
        move();
    }

    public void move(){
        double nx = x +  (Math.cos(Math.toRadians(direction)) * speed);
        double ny = y +  (Math.sin(Math.toRadians(direction)) * speed);
        boolean canmove = true;
        for(int i = 0; i <= width; i++) {
            if (!getArea().isWalkable(((int) nx) + i, (int) ny)) {
                canmove = false;
            }
        }
        if(canmove) {
            x = nx;
            y = ny;
        }

    }

    public void moveTowardsPoint(Point p){

    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
