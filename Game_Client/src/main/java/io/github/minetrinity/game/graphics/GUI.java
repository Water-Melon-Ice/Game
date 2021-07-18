package io.github.minetrinity.game.graphics;

import java.awt.*;

import io.github.minetrinity.game.time.Tickable;


public abstract class GUI extends Container implements Paintable, Tickable { // GraphicalUserInterface

    public abstract void open();

    public abstract void close();

    @Override
    public void paintAll(Graphics g) {
        paint(g);
        super.paintAll(g);
    }
}
