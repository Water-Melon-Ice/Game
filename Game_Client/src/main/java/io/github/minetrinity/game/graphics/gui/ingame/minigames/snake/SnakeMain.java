package io.github.minetrinity.game.graphics.gui.ingame.minigames.snake;


import io.github.minetrinity.game.graphics.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


public class SnakeMain extends GUI {

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    private static final int ALL_SQUARES = WIDTH * HEIGHT;
    private static final int RAND_A = 29;
    //private static final String [] FOODS_IMAGE = new String[];

    private final int x[] = new int[ALL_SQUARES];
    private final int y[] = new int[ALL_SQUARES];

    private Image apple;

    private int apple_x;
    private int apple_y;

    private int squares;

    private boolean leftDirection = false;
    private boolean  rightDirection = true;
    private boolean  upDirection = false;
    private boolean  downDirection = false;

    private boolean inGame = true;

    public Board() {

        createBoard();
    }

    private void createBoard() {

        addKeyListener(new TAdapter());

        loadImages();
        startGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("   ");
        apple = iid.getImage();
    }
    //Apfel Image Laden

    private void startGame() {

        squares = 3;

        for (int z = 0; z < squares; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        placeApple();
    }

    private void placeApple() {

        int a = (int) (Math.random() * RAND_A);
        apple_x = ((a * SQUARE_SIZE));

        a = (int) (Math.random() * RAND_A);
        apple_y = ((a * SQUARE_SIZE));
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

        if (leftDirection) {
            y[0] -= SQUARE_SIZE;
        }

        if (leftDirection) {
            y[0] += SQUARE_SIZE;
        }
    }

    public void run(ActionEvent e) {

        if (inGame) {

            eaten();
            move();
        }
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void tick() {

    }
}
