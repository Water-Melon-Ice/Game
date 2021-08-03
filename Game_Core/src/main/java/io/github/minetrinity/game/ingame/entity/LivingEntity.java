package io.github.minetrinity.game.ingame.entity;

public class LivingEntity extends Entity {

    private int health;

    @Override
    public void tick() {
        super.tick();
        if(!isAlive()) kill();
    }

    public boolean isAlive(){
        return health > 0;
    }

    public final void kill(){
        area.remove(this);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
