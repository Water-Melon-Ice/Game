package io.github.minetrinity.game.ingame.entity.entities;

import io.github.minetrinity.game.ingame.entity.MovingEntity;

import java.awt.*;

public class GhostEntity extends MovingEntity {

    public GhostEntity(){
        super();
        maxSpeed = 10;
        texture = "Geist(Laser).gif";
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void move() {
        hitbox.getLocation().translate(entityVector.getDirection().x, entityVector.getDirection().y);
        entityVector.getDirection().x *= airDrag;
        entityVector.getDirection().y *= airDrag;
        if(entityVector.getDirection().distance(new Point()) < maxSpeed){
            double rdx = Math.random() * maxSpeed - maxSpeed / 2;
            double rdy = Math.random() * maxSpeed - maxSpeed / 2;
            entityVector.getDirection().x += rdx;
            entityVector.getDirection().y += rdy;
            if(entityVector.getDirection().x > 0)
                texture = "Geist(Laser).gif";
            else texture = "Geist(Laser).gif";
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
}
