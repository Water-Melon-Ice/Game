package io.github.minetrinity.game.ingame.entity.entities;

import io.github.minetrinity.game.ingame.entity.MovingEntity;


public class PlayerEntity extends MovingEntity {

    public PlayerEntity() {
        super();
        setMaxSpeed(0.0625);
        texture = "Character(L).gif";
    }

    @Override
    public void tick() {
        super.tick();
    }


}
