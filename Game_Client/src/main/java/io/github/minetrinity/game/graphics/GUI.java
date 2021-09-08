package io.github.minetrinity.game.graphics;

import java.awt.*;

import io.github.minetrinity.game.graphics.components.GComponent;
import io.github.minetrinity.game.graphics.components.GOverlay;
import io.github.minetrinity.game.time.Tickable;

import javax.swing.*;


public abstract class GUI extends GComponent implements Paintable, Tickable { // GraphicalUserInterface

    public abstract void open();

    public abstract void close();

    public int overlays = 0;

    public GUI() {
        super(Window.getInstance().getSize());
    }

    @Override
    public void tick() {

    }
}
