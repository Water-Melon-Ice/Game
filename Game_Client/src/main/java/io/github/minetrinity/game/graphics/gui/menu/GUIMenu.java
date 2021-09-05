package io.github.minetrinity.game.graphics.gui.menu;

import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.components.GButton;
import io.github.minetrinity.game.graphics.gui.TestGui;
import io.github.minetrinity.game.graphics.gui.minigames.slimehead.SlimeMain;
import io.github.minetrinity.game.graphics.gui.minigames.snake.SnakeMain;

import java.awt.*;

public class GUIMenu extends GUI {


    @Override
    public void open() {
        GButton play = new GButton(getWidth() / 2 - 256, getHeight() / 2 - 512, 512,256, "Play.png"){
            @Override
            public void onClick() {
                Window.getInstance().setGUI(new TestGui());
            }
        };
        add(play);

        GButton snake = new GButton(getWidth() / 2 - 256, getHeight() / 2 - 256, 512,256, "Credits.png"){
            @Override
            public void onClick() {
                Window.getInstance().setGUI(new SnakeMain());
            }
        };
        add(snake);

        GButton slime = new GButton(getWidth() / 2 - 256, getHeight() / 2, 512,256, "Credits.png"){
            @Override
            public void onClick() {
                Window.getInstance().setGUI(new SlimeMain());
            }
        };
        add(slime);

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
