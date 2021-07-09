package io.github.minetrinity.game.ingame.event;

import io.github.minetrinity.game.ingame.entity.Entity;

public interface Event {

    void action(Entity activator);

}
