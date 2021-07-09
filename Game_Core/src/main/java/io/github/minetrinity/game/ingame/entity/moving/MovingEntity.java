package io.github.minetrinity.game.ingame.entity.moving;

import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.math.AccuratePoint;

public abstract class MovingEntity extends Entity {

    public MovingEntity(AccuratePoint location){
        super(location);
    }

}
