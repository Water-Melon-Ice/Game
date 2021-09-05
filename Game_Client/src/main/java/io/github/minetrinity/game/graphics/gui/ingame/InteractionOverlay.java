package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.components.GOverlay;

import java.awt.*;

public class InteractionOverlay extends GOverlay {

    public InteractionOverlay() {
        super(0, Window.getInstance().getSize().height / 2, Window.getInstance().getHeight() / 2, Window.getInstance().getWidth());
    }

    @Override
    public void paint(Graphics g) {
        paintBorder(g);
        paintText(g);
    }

    public void paintBorder(Graphics g){

    }

    public void paintText(Graphics g){

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    public void close() {
        super.close();
    }
}
