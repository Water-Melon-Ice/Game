package io.github.minetrinity.game.math;

import static java.lang.Math.*;

public class AccuratePoint {

    public static final AccuratePoint zero = new AccuratePoint(0,0);

    public double dx;
    public double dy;

    public AccuratePoint(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public double distance(AccuratePoint p){
        return sqrt(pow(dx - p.dx, 2.0d) + pow(dy - p.dy, 2.0d));
    }

}
