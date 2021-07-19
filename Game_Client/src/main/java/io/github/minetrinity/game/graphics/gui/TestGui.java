package io.github.minetrinity.game.graphics.gui;

import io.github.minetrinity.game.graphics.*;
import io.github.minetrinity.game.ingame.world.Area;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TestGui extends GUI {

    BufferedImage world;
    Area a;

    @Override
    public void open() {
        Texture l1 = Textures.getByName("Testworld.png");
        Texture l2 = Textures.getByName("Testworld2.png");
        LayeredTexture lt = new LayeredTexture(l1, l2);
        a = Area.from(lt);

    }

    @Override
    public void close() {

    }

    @Override
    public void paint(Graphics g) {
        world = a.toImage();
        g.drawImage(world, 0,0, null);
    }

    @Override
    public void tick() {
        ((AnimatedTexture) Textures.getByName("Wasser.gif")).tick();
    }
}
