package io.github.minetrinity.game.graphics;

import java.awt.*;

import io.github.minetrinity.game.time.Tickable;

import javax.swing.*;


public abstract class GUI extends Container implements Paintable { // GraphicalUserInterface

    public abstract void open();
    public abstract void close();

    public GUI(){
        setSize(Window.getInstance().getSize());
    }

    @Override
    public void paintAll(Graphics g) {
        paint(g);
        for (Component c : getComponents()) {
            if(c == null) continue;
            Graphics g2 = g.create(c.getX(), c.getY(), c.getWidth(), c.getHeight());
            c.paintAll(g2);
            g2.dispose();
        }
    }

}
