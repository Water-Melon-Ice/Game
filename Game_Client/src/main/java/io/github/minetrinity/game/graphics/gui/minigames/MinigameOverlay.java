package io.github.minetrinity.game.graphics.gui.minigames;

import io.github.minetrinity.game.graphics.components.GOverlay;

public abstract class MinigameOverlay extends GOverlay {

    @Override
    public void close() {

    }

    public abstract int closeMinigame();

    public final void die(){
        add(new RetryOverlay(this, closeMinigame()));
    }
}
