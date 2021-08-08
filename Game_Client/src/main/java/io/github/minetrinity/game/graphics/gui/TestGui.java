package io.github.minetrinity.game.graphics.gui;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.Game;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.components.GButton;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.ingame.entity.MovingEntity;
import io.github.minetrinity.game.ingame.entity.entities.GhostEntity;
import io.github.minetrinity.game.io.AreaIO;
import io.github.minetrinity.game.load.ResourceFactory;
import io.github.minetrinity.game.load.Resources;
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

        for (int i = 0; i < 1; i++) {
            Entity e = new GhostEntity();
            e.setLocation(new Point(400 , 400));
            World.getCurrent().add(e);
        }

        Camera c = new Camera();
        c.setSize(this.getSize());
        add(c);

        Game.getInstance().add(World.getCurrent());

    }

    @Override
    public void close() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("" + Client.getInstance().getActualticks(), 10, 10);
    }

}
