package io.github.minetrinity.game.graphics.gui.minigames;

import io.github.minetrinity.game.graphics.components.GOverlay;
import io.github.minetrinity.game.ingame.Player;
import io.github.minetrinity.game.ingame.entity.entities.PlayerEntity;
import io.github.minetrinity.game.ingame.world.World;

public abstract class MinigameOverlay extends GOverlay {

    @Override
    public void close() {
        Player.getEntity().setLocation(Player.getEntity().getX(), Player.getEntity().getY() + 2);
    }

    public abstract int closeMinigame();

    public final void die(){
        add(new RetryOverlay(this, closeMinigame()));
    }
}
