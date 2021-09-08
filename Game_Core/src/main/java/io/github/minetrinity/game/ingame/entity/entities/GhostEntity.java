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
}
