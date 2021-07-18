package io.github.minetrinity.game.graphics.gui.ingame.minigames.snake;


import io.github.minetrinity.game.graphics.GUI;


import java.awt.*;

public class SnakeMain extends GUI {

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    //private static final String [] FOODS_IMAGE = new String[];

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private Image FoodImage;
    private int FoodX;
    private int FoodY;
    private boolean gameOer;
    private int currentDirection;

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
