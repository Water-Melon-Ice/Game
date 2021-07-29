package io.github.minetrinity.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class Controls implements KeyListener {

    private static Controls instance;

    private static ArrayList<Character> pressedKeys = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        char c = (char) e.getExtendedKeyCode();
        pressedKeys.add((Character) c);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = (char) e.getExtendedKeyCode();
        pressedKeys.removeAll(Collections.singleton((Character) c));
    }

    public static boolean isKeyPressed(char key){
        if(pressedKeys.contains(key)) return true;
        return false;
    }

    public static Controls getInstance() {
        if (instance == null) instance = new Controls();
        return instance;
    }

    public static int getY(){
        int y = 0;
        if(isKeyPressed( 'W') || isKeyPressed((char) KeyEvent.VK_UP)){
            y--;
        }
        if(isKeyPressed('S') || isKeyPressed((char) KeyEvent.VK_DOWN)){
            y++;
        }
        return y;
    }

    public static int getX(){
        int x = 0;
        if(isKeyPressed('D') || isKeyPressed((char) KeyEvent.VK_RIGHT)){
            x++;
        }
        if(isKeyPressed('A') || isKeyPressed((char) KeyEvent.VK_LEFT)){
            x--;
        }
        return x;
    }

    private Controls(){}
}
