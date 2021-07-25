package io.github.minetrinity.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Controls implements KeyListener {

    private static Controls instance;

    private static ArrayList<Character> pressedKeys = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add((char) e.getExtendedKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((char) e.getExtendedKeyCode());
    }

    public static boolean isKeyPressed(char key){
        if(pressedKeys.contains(key)) return true;
        return false;
    }

    public static Controls getInstance() {
        if (instance == null) instance = new Controls();
        return instance;
    }

    private Controls(){}
}
