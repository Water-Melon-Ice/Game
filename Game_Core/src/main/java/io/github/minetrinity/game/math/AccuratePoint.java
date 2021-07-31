package io.github.minetrinity.game.math;

import java.awt.*;

import static java.lang.Math.*;

public class AccuratePoint {

    public static final AccuratePoint zero = new AccuratePoint(0,0);

    public static AccuratePoint toAccuratePoint(Point p){
        return new AccuratePoint(p.x, p.y);
    }

    public double dx;
    public double dy;

    public AccuratePoint(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public double distance(AccuratePoint p){
        return sqrt(pow(p.dx - dx, 2.0d) + pow(p.dy - dy, 2.0d));
    }

    public void multiply(double factor){
        dx *= factor;
        dy *= factor;
    }

    public double sum(){
        return dx + dy;
    }

}
