package io.github.minetrinity.game.ingame;

import io.github.minetrinity.game.ingame.entity.entities.PlayerEntity;

public class Player {

    private static PlayerEntity entity;

    public static PlayerEntity getEntity() {
        if(entity == null) entity = new PlayerEntity();
        return entity;
    }
}
