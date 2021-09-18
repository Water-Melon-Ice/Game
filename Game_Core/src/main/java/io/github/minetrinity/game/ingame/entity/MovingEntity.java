package io.github.minetrinity.game.ingame.entity;

import java.awt.*;

public class MovingEntity extends LivingEntity {

    protected double speed = 10;
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
        x +=  (Math.cos(Math.toRadians(direction)) * speed);
        y +=  (Math.sin(Math.toRadians(direction)) * speed);
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
