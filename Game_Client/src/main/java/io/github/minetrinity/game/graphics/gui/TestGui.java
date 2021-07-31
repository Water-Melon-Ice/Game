package io.github.minetrinity.game.graphics.gui;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.Game;
import io.github.minetrinity.game.ingame.entity.Entity;
import io.github.minetrinity.game.ingame.entity.MovingEntity;
import io.github.minetrinity.game.io.AreaIO;
import io.github.minetrinity.game.load.Resources;
import io.github.minetrinity.game.load.TextureFactory;
import io.github.minetrinity.game.graphics.*;
import io.github.minetrinity.game.graphics.gui.ingame.Camera;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.World;
import io.github.minetrinity.game.math.AccuratePoint;

import java.awt.*;

public class TestGui extends GUI {


    @Override
    public void open() {
        Area a = AreaIO.create(Resources.defaultResPath, "start");
        World.setCurrent(a);


        for (int i = 0; i < 10000; i++) {
            Entity e = new MovingEntity();
            e.setLocation(new AccuratePoint(400 , 400));
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
