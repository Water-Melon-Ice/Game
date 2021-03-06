package io.github.minetrinity.game.graphics.gui;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.Game;
import io.github.minetrinity.game.graphics.gui.minigames.slimehead.SlimeMain;
import io.github.minetrinity.game.graphics.gui.minigames.snake.SnakeMain;
import io.github.minetrinity.game.ingame.Player;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.ingame.entity.entities.GhostEntity;
import io.github.minetrinity.game.input.KeyBindings;
import io.github.minetrinity.game.io.AreaIO;
import io.github.minetrinity.game.io.Resources;
import io.github.minetrinity.game.graphics.*;
import io.github.minetrinity.game.graphics.gui.ingame.Camera;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;

public class TestGui extends GUI {


    @Override
    public void open() {
        Area a = AreaIO.create(Resources.defaultResPath, "start");

        World.setCurrent(a);

        /*for (int i = 0; i < 40; i++) {
            Entity e = new GhostEntity();
            e.setLocation(20, 20);
            World.getCurrent().add(e);
        }*/

        Game.getInstance().add(World.getCurrent());
        World.getCurrent().add(Player.getEntity());

        Camera c = new Camera(Player.getEntity());
        c.setSize(this.getSize());
        add(c);


    }

    @Override
    public void close() {

    }

    /**
     * TODO: TEMPORARY
     */
    @Override
    public void tick() {

        if(getComponents().size() == 1) {
            getComponents().get(0).setVisible(true);
        }
    }
}
