package io.github.minetrinity.game.math;

public class MathUtils {

    public static double getDegree(double x, double y){
        return Math.toDegrees(Math.atan2(y,x));
    }

    public static double getDegree(double x, double y, double originx, double originy){
        x -= originx;
        y -= originy;
        return Math.toDegrees(Math.atan2(y,x));
    }

}
