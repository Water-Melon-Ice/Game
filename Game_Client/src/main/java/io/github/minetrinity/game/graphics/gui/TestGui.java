package io.github.minetrinity.game.graphics.gui;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.io.AreaIO;
import io.github.minetrinity.game.load.Resources;
import io.github.minetrinity.game.load.TextureFactory;
import io.github.minetrinity.game.graphics.*;
import io.github.minetrinity.game.graphics.gui.ingame.Camera;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.World;

import java.awt.*;

public class TestGui extends GUI {


    @Override
    public void open() {
        Area a = AreaIO.create(Resources.defaultResPath, "start");
        World.getInstance().setCurrent(a);

        Camera c = new Camera();
        c.setSize(this.getSize());
        add(c);

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
