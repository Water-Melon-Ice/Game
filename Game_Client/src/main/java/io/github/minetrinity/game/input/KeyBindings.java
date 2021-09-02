package io.github.minetrinity.game.input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public final class KeyBindings {

    private static HashMap<String, Character> bindingMap = new HashMap<>();

    public static boolean isBindingPressed(String binding){
        return Controls.isKeyPressed(bindingMap.get(binding));
    }

    public static void addBinding(char key){
        
    }

    public static int getX(){
        int x = 0;
        if(isBindingPressed("RIGHT.CHAR") || isBindingPressed("RIGHT.ARROW")){
            x++;
        }
        if(isBindingPressed("LEFT.CHAR") || isBindingPressed("LEFT.ARROW")){
            x--;
        }
        return x;
    }

    public static int getY(){
        int y = 0;
        if(isBindingPressed("UP.CHAR") || isBindingPressed("UP.ARROW")){
            y--;
        }
        if(isBindingPressed("DOWN.CHAR") || isBindingPressed("DOWN.ARROW")){
            y++;
        }
        return y;
    }

}
