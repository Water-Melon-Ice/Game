package io.github.minetrinity.game.ingame.world;

public class World {

    private static World instance;

    public static World getInstance() {
        if(instance == null) instance = new World();
        return instance;
    }

    protected Area current;

    public void setCurrent(Area current) {
        this.current = current;
    }

    public Area getCurrent() {
        return current;
    }
}
