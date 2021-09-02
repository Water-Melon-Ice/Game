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
        hitbox.x += (int) (Math.cos(Math.toRadians(direction)) * speed);
        hitbox.y += (int) (Math.sin(Math.toRadians(direction)) * speed);
        speed *= airDrag;
        if(speed < maxSpeed){
            int rd = (int) (Math.random() * maxSpeed);
            speed += rd;
            speed = (int) Math.min(maxSpeed, speed);
        }

    }
}
