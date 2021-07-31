package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.math.AccuratePoint;

public class LivingEntity extends Entity {

    private int health;

    protected int hitboxWidth, hitboxHeight;

    @Override
    public void tick() {
        super.tick();
        if(health < 0) area.remove(this);
    }

    public boolean isHit(AccuratePoint p){
        if(p.dx < location.dx) return false;
        if(p.dx > location.dx + hitboxWidth) return false;

        if(p.dy < location.dy) return false;
        if(p.dy > location.dy + hitboxHeight) return false;
        return true;
    }

}
