package io.github.minetrinity.game.graphics.gui.minigames;

import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.components.GButton;
import io.github.minetrinity.game.graphics.components.GOverlay;
import io.github.minetrinity.game.graphics.components.GTextArea;
import io.github.minetrinity.game.graphics.gui.menu.GUIMenu;

import java.awt.*;

public class RetryOverlay extends GOverlay {

    private MinigameOverlay ming;
    private int score;

    public RetryOverlay(MinigameOverlay ming, int score){
        this.score = score;
        this.ming = ming;

        //Retry
        GButton play = new GButton(getWidth() / 2 - 512, getHeight() / 2, 512,256, "Play.png", "CONTINUE"){
            @Override
            public void onClick() {
                getParent().destroy();
            }
        };
        add(play);

        //Exit
        GButton snake = new GButton(getWidth() / 2, getHeight() / 2, 512,256, "Credits.png", "EXIT"){
            @Override
            public void onClick() {
               getParent().getParent().destroy();
            }
        };
        add(snake);

        GTextArea gt = new GTextArea(getWidth() / 2 - 50, getHeight() / 2 -128, 100, 100, "" + score, Color.white);
        add(gt);

    }

    @Override
    public void close() {
        super.close();
        ming.open();
    }
}
