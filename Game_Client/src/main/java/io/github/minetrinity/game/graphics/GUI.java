package io.github.minetrinity.game.graphics;

import java.awt.*;

import io.github.minetrinity.game.graphics.components.GComponent;
import io.github.minetrinity.game.time.Tickable;

import javax.swing.*;


public abstract class GUI extends GComponent implements Paintable, Tickable { // GraphicalUserInterface

    public abstract void open();
    public abstract void close();

    public GUI(){
        super(Window.getInstance().getSize());
    }

    @Override
    public void paintAll(Graphics g) {
        paint(g);
        for (GComponent c : getComponents()) {
            if(c == null) continue;
            Graphics g2 = g.create(c.getX(), c.getY(), c.getWidth(), c.getHeight());
            c.paintAll(g2);
            g2.dispose();
        }
    }

    @Override
    public void tick() {

    }
}
