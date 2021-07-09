package io.github.minetrinity.game.graphics.gui;

import java.awt.*;

public abstract class GUI extends Container{ // GraphicalUserInterface



    public abstract void open();
    public abstract void close();

    public abstract void render(Graphics g);

    @Override
    public void paintAll(Graphics g) {
        render(g);
        super.paintAll(g);
    }
}
