package io.github.minetrinity.game.graphics.components;

import io.github.minetrinity.game.input.Controls;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GDragable extends GComponent {

    private Point mousedown;


    public GDragable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void tick() {
        super.tick();
        if(isLeftClicked()){
            mousedown = Controls.getMouseLocation();
        }else if(mousedown != null && !Controls.isMousePressed(1)){
            mousedown = null;
        }
    }

    public boolean isDragged() {
        return mousedown != null;
    }

    public int getDeltaX() {
        if (mousedown == null) return 0;
        int c = Controls.getMouseLocation().x;
        return c - mousedown.x;
    }


    public int getDeltaY() {
        if (mousedown == null) return 0;
        int c = Controls.getMouseLocation().y;
        return c - mousedown.y;
    }

    public synchronized int getDeltaXClear() {
        if (mousedown == null) {
            return 0;
        } else {
            int c = Controls.getMouseLocation().x - mousedown.x;
            mousedown.x = mousedown.x + c;
            return c;
        }
    }


    public synchronized int getDeltaYClear() {
        if (mousedown == null) {
            return 0;
        } else {
            int c = Controls.getMouseLocation().y - mousedown.y;
            mousedown.y = mousedown.y + c;
            return c;
        }
    }

    public Point getMousedown() {
        return mousedown;
    }
}
