package io.github.minetrinity.game.graphics;

import java.awt.*;
import java.awt.event.*;

import io.github.minetrinity.game.time.Tickable;


public abstract class GUI extends Container implements Paintable, Tickable, KeyListener, MouseListener, MouseWheelListener { // GraphicalUserInterface

    public abstract void open();

    public abstract void close();

    @Override
    public void paintAll(Graphics g) {
        paint(g);
        super.paintAll(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {}
}
