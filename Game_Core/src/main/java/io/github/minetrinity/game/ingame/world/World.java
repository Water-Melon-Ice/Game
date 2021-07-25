package io.github.minetrinity.game.ingame.world;

import java.util.ArrayList;

public class World {

    private static World instance;

    public static World getInstance() {
        if(instance == null) instance = new World();
        return instance;
    }

    protected Area start;
    protected ArrayList<Area> list;

}
