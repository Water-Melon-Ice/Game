package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.math.Vector;

import java.awt.*;

public class MovingEntity extends LivingEntity {

    protected Vector entityVector;

    private double airDrag = 0.9;
    private double maxSpeed = 20;

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
        hitbox.getLocation().translate(entityVector.getDirection().x, entityVector.getDirection().y);
        entityVector.getDirection().x *= airDrag;
        entityVector.getDirection().y *= airDrag;
        if(entityVector.getDirection().distance(new Point()) < maxSpeed){
            double rdx = Math.random() * maxSpeed - maxSpeed / 2;
            double rdy = Math.random() * maxSpeed - maxSpeed / 2;
            entityVector.getDirection().x += rdx;
            entityVector.getDirection().y += rdy;
            if(entityVector.getDirection().x > 0)
                texture = "Character(R).gif";
            else texture = "Character(L).gif";
        }
        if(hitbox.x < 0 && entityVector.getDirection().x < 0){
            hitbox.x = 0;
            entityVector.getDirection().x = -entityVector.getDirection().x;
        }
        if(hitbox.y < 0 && entityVector.getDirection().y < 0){
            hitbox.y = 0;
            entityVector.getDirection().y = -entityVector.getDirection().y;
        }
        if(hitbox.x > 1920 - 16 && entityVector.getDirection().x > 0){
            hitbox.x = 1920 - 16;
            entityVector.getDirection().x = -entityVector.getDirection().x;
        }
        if(hitbox.y > 1080 - 32 && entityVector.getDirection().y > 0){
            hitbox.y = 1080 - 32;
            entityVector.getDirection().y = -entityVector.getDirection().y;
        }
    }

    @Override
    public void setLocation(Point location) {
        super.setLocation(location);
    }
}
