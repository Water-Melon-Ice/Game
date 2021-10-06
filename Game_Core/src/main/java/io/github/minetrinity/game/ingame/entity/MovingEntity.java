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
        double nx = getXDistance();
        double ny = getYDistance();
        if (!isObstrukted(nx, ny)) {
            teleport(nx, ny);
        }else if(!isObstrukted(getX(), ny)){
            teleport(getX(), ny);
            snapToGrid();
        }else if(!isObstrukted(nx, getY())){
            teleport(nx, getY());
            snapToGrid();
        }else{
            snapToGrid();
        }




    }

    public void teleport(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isObstrukted(double x, double y) {
        for (int i = 0; i < width + 1; i++) {
            if (getArea().isObstrukted(((int) x) + i, (int) y)) {
                return true;
            }
        }
        return false;
    }

    //todo: snaps to obstrukted tiles too
    public void snapToGrid() {
        setLocation(Math.round(getX()), Math.round(getY()));
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getXDistance() {
        return x + (Math.cos(Math.toRadians(direction)) * speed);
    }

    public double getYDistance() {
        return y + (Math.sin(Math.toRadians(direction)) * speed);
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
