package io.github.minetrinity.game.ingame.entity;

public class LivingEntity extends Entity {

    private int health = 1;

    @Override
    public void tick() {
        super.tick();
        if(!isAlive()) kill();
    }

    public boolean isAlive(){
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void onDamage(Entity damage){

    }


}
