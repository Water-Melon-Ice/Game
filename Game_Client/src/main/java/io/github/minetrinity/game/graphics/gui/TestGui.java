package io.github.minetrinity.game.graphics.gui;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.graphics.*;
import io.github.minetrinity.game.ingame.world.Area;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TestGui extends GUI {

    BufferedImage world;
    Area a;

    @Override
    public void open() {
        Texture l1 = TextureFactory.getByName("pixil-start-map.png");
        Texture l2 = TextureFactory.getByName("pixil-start-map2.png");

        LayeredTexture lt = new LayeredTexture(l1, l2);
        a = Area.from(lt);
    }

    @Override
    public void close() {

    }

    @Override
    public void paint(Graphics g) {
        //TODO: world = a.toImage();
        g.drawImage(world, 0,0, null);
        g.setColor(Color.green);
        g.drawString("" + Client.getInstance().getActualticks(), 10, 10);
    }

    @Override
    public void tick() {

    }
}
