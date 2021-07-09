package io.github.minetrinity.game.ingame.event;

import io.github.minetrinity.game.ingame.entity.Entity;

import java.awt.*;

public abstract class LocationDependentEvent {

    Point location;

    public LocationDependentEvent(Point location){
        this.location = location;
    }

    public abstract void action(Entity activator);

}
