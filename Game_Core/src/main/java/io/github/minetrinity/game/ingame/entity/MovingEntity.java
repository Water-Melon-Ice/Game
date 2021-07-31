package io.github.minetrinity.game.ingame.entity;

import io.github.minetrinity.game.math.AccuratePoint;
import io.github.minetrinity.game.math.Vector;

public class MovingEntity extends LivingEntity {

    protected Vector entityVector;

    private double airDrag = 0.9;
    private double maxSpeed = 20;

    public MovingEntity(){
        super();
        entityVector = new Vector(location, new AccuratePoint(1,1));
    }

    @Override
    public void tick() {
        super.tick();

        entityVector.move(1);
        entityVector.getDirection().multiply(airDrag);
        if(entityVector.getDirection().distance(AccuratePoint.zero) < maxSpeed){
            double rdx = Math.random() * maxSpeed - maxSpeed / 2;
            double rdy = Math.random() * maxSpeed - maxSpeed / 2;
            entityVector.getDirection().dx += rdx;
            entityVector.getDirection().dy += rdy;
            if(entityVector.getDirection().dx > 0)
                texture = "Character(R).gif";
            else texture = "Character(L).gif";
        }
        if(location.dx < 0 && entityVector.getDirection().dx < 0){
            location.dx = 0;
            entityVector.getDirection().dx = -entityVector.getDirection().dx;
        }
        if(location.dy < 0 && entityVector.getDirection().dy < 0){
            location.dy = 0;
            entityVector.getDirection().dy = -entityVector.getDirection().dy;
        }
        if(location.dx > 1920 - 16 && entityVector.getDirection().dx > 0){
            location.dx = 1920 - 16;
            entityVector.getDirection().dx = -entityVector.getDirection().dx;
        }
        if(location.dy > 1080 - 32 && entityVector.getDirection().dy > 0){
            location.dy = 1080 - 32;
            entityVector.getDirection().dy = -entityVector.getDirection().dy;
        }

    }

    @Override
    public void setLocation(AccuratePoint location) {
        super.setLocation(location);
        entityVector = new Vector(location, entityVector.getDirection());
    }
}
