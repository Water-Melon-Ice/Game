package io.github.minetrinity.game.graphics.gui.menu;

import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.components.GButton;
import io.github.minetrinity.game.graphics.gui.TestGui;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GUIMenu extends GUI {


    @Override
    public void open() {
        GButton play = new GButton(getWidth() / 2 - 256, getHeight() / 2 - 512, 512,256, null, "Play.png"){
            @Override
            public void onClick() {
                Window.getInstance().setGUI(new TestGui());
            }
        };
        add(play);

        GButton snake = new GButton(getWidth() / 2 - 256, getHeight() / 2 - 256, 512,256, null, "Credits.png"){
            @Override
            public void onClick() {
                Window.getInstance().setGUI(new TestGui());
            }
        };
        add(snake);

    }

    @Override
    public void close() {

    }

    @Override
    public void paint(Graphics g) {

    }

    public void tick() {

    }
}
