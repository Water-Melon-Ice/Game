package io.github.minetrinity.game.ingame.entity;

public class MovingEntity extends LivingEntity {

    protected double speed = 0;
    protected double direction = 0.0;

    protected double maxSpeed = 1;

    public MovingEntity() {
        super();
    }

    @Override
    public void tick() {
        super.tick();
        move();
    }

    public void move() {
        double nx = x + getXDistance();
        double ny = y + getYDistance();
        if (isMoveable(nx, ny)) {
            teleport(nx, ny);
        }else if(isMoveable(getX(), ny)){
            teleport(getX(), ny);
        }else if(isMoveable(nx, getY())) {
            teleport(nx, getY());
        }




    }

    public void teleport(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isMoveable(double x, double y) {
        for (int i = 0; i < ((int) width) + 1; i++) {
            if (getArea().isObstrukted(((int) x) + i, (int) y)) {
                return false;
            }
        }

        if(getArea().isObstrukted((int) (x + width % 1), (int) y)) return false;
        return true;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getXDistance() {
        return (Math.cos(Math.toRadians(direction)) * speed);
    }

    public double getYDistance() {
        return (Math.sin(Math.toRadians(direction)) * speed);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
