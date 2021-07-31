package io.github.minetrinity.game.graphics.gui.ingame.minigames.snake;


import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.ingame.world.World;
import io.github.minetrinity.game.load.ResourceFactory;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


public class SnakeMain extends GUI { //I did some commets :D @Minetrinity

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int SQUARE_SIZE = WIDTH / 20;
    private static final int ALL_SQUARES = WIDTH * HEIGHT;


    private final int x[] = new int[ALL_SQUARES];
    private final int y[] = new int[ALL_SQUARES];

    private int apple_x;
    private int apple_y;

    private int squares;

    private boolean leftDirection = false;
    private boolean  rightDirection = true;
    private boolean  upDirection = false;
    private boolean  downDirection = false;

    Texture TestSnake = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName("Weg.png");
    Texture apple = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName("Pixel-apple.png");

    private void placeApple() {

        int a = (int) (Math.random() * WIDTH);
        apple_x = a;

        a = (int) (Math.random() * HEIGHT);
        apple_y = a;
    }

    private void eaten() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            squares++;
            placeApple();
        }
    }

    private void move() {

        for (int z = squares; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= SQUARE_SIZE;
        }

        if (rightDirection) {
            x[0] += SQUARE_SIZE;
        }

        if (upDirection) {
            y[0] -= SQUARE_SIZE;
        }

        if (downDirection) {
            y[0] += SQUARE_SIZE;
        }
    }


    @Override
    public void open() { //called on opening of the GUI

        squares = 3;

        for (int z = 0; z < squares; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        placeApple();

    }

    @Override
    public void close() {

    }

    @Override
    public void paint(Graphics g) { //render snake and background to Graphics here.

        eaten();
        move();


        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++){
                g.drawImage(TestSnake.getImage(), x, y, null);
            }
        }

        g.drawImage(apple.getImage(),apple_x,apple_y,null);
    }

}
