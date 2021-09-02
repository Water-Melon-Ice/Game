package io.github.minetrinity.game.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

public class Controls implements KeyListener, MouseListener {

    private static Controls instance;

    private static ArrayList<Character> pressedKeys = new ArrayList<>();
    private static ArrayList<Integer> pressedMouseButtons = new ArrayList<>();

    private static boolean isMouseInBounds = true;

    private static double directioncache = 0.0;

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
        return pressedKeys.contains(key);
    }

    public static Controls getInstance() {
        if (instance == null) instance = new Controls();
        return instance;
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


    public static int getMouseX(){
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    public static int getMouseY(){
        return MouseInfo.getPointerInfo().getLocation().y;
    }

    public static Point getMouseLocation(){
        return MouseInfo.getPointerInfo().getLocation();
    }

    public static int getNumberOfMouseButtons(){
        return MouseInfo.getNumberOfButtons();
    }

    public static boolean isIsMouseInBounds(){
        return isMouseInBounds;
    }

    public static boolean isMousePressed(int mouseButton){
        return pressedMouseButtons.contains(mouseButton);
    }

    private Controls(){}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        pressedMouseButtons.add(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedMouseButtons.removeAll(Collections.singleton(e.getButton()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isMouseInBounds = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isMouseInBounds = false;
    }
}
