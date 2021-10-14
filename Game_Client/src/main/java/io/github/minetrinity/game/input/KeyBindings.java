package io.github.minetrinity.game.input;

import io.github.minetrinity.game.math.MathUtils;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public final class KeyBindings {

    private static HashMap<String, Character> bindingMap = new HashMap<>();

    public static boolean isBindingPressed(String binding){
        if(bindingMap.get(binding) == null) return false;
        return Controls.isKeyPressed(bindingMap.get(binding));
    }

    public static boolean isBindingPressed(String binding, Character defaultChar){
        Character character = bindingMap.get(binding);
        if(character == null) character = defaultChar;
        return Controls.isKeyPressed(character);
    }

    public static void addBinding(char key){
        
    }

    public static int getX(){
        int x = 0;
        if(isBindingPressed("RIGHT.CHAR", 'D') || isBindingPressed("RIGHT.ARROW" )){
            x++;
        }
        if(isBindingPressed("LEFT.CHAR", 'A') || isBindingPressed("LEFT.ARROW")){
            x--;
        }
        return x;
    }

    public static int getY(){
        int y = 0;
        if(isBindingPressed("UP.CHAR", 'W') || isBindingPressed("UP.ARROW")){
            y--;
        }
        if(isBindingPressed("DOWN.CHAR", 'S') || isBindingPressed("DOWN.ARROW")){
            y++;
        }
        return y;
    }

    public static double getKeyDirection(){
        return MathUtils.getDegree(getX(), getY());
    }

    public static boolean hasKeyDirection(){
        return getX() != 0 || getY() != 0;
    }

}
