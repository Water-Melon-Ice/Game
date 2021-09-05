package io.github.minetrinity.game.graphics.gui.ingame.minigames;

import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.gui.ingame.minigames.RetryOverlay;

public abstract class MinigameGUI extends GUI {

    @Override
    public void close() {

    }

    public abstract int closeMinigame();

    public final void die(){
        add(new RetryOverlay(this, closeMinigame()));
    }
}
