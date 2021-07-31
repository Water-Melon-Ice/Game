package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.time.Tickable;

public class World {

    private static Area current;

    public static void setCurrent(Area current) {
        World.current = current;
    }

    public static Area getCurrent() {
        return current;
    }

}
