package io.github.minetrinity.game.graphics.gui.minigames;

import io.github.minetrinity.game.graphics.GUI;

public abstract class MinigameOverlay extends GUI {

    @Override
    public void close() {

    }

    public abstract int closeMinigame();

    public final void die(){
        add(new RetryOverlay(this, closeMinigame()));
    }
}
