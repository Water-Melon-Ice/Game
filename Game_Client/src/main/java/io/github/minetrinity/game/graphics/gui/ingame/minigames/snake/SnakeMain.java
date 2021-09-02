package io.github.minetrinity.game.graphics.gui.ingame.minigames.snake;


import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.io.Resources;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SnakeMain extends GUI { //I did some commets :D @Minetrinity

    private static final int WIDTH = 1000;
    private static final int HEIGHT = WIDTH;
    private static final int ROW = WIDTH / 59;
    private static final int SQUARE_SIZE = WIDTH / HEIGHT;
    private static final int ALL_SQUARES = WIDTH * HEIGHT;


    private final int[] x = new int[ALL_SQUARES];
    private final int[] y = new int[ALL_SQUARES];

    private int apple_x;
    private int apple_y;

    private int squares;

    private boolean leftDirection = false;
    private boolean  rightDirection = true;
    private boolean  upDirection = false;
    private boolean  downDirection = false;

    private final List<Point> snakebody = new ArrayList<>();
    private Point snakeHead;

    Texture background = (Texture) Resources.getResource("Weg.png");
    Texture apple = (Texture) Resources.getResource("Pixel-apple.png");
    Texture snakeHeadImage = (Texture) Resources.getResource("snakehead.png");
    Texture snakeBodyImage = (Texture) Resources.getResource("snakebody.png");
    Texture snakeTailImage = (Texture) Resources.getResource("snakebutt.png");


    private void placeApple() {

        int a = (int) (Math.random() * WIDTH);
        apple_x = a;

        a = (int) (Math.random() * HEIGHT);
        apple_y = a;
    }

    private void creatSnake(Graphics g) {
        g.drawImage(snakeHeadImage.getImage(),WIDTH / 2,HEIGHT / 2,null);
        g.drawImage(snakeBodyImage.getImage(),WIDTH / 2,HEIGHT / 2 + ROW,null);
        g.drawImage(snakeTailImage.getImage(),WIDTH / 2,HEIGHT / 2 + 2 * ROW,null);

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

        for (int i = 0; i < 3; i++) {
            snakebody.add(new Point(5, WIDTH / 2));
        }
        snakeHead = snakebody.get(0);
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
                g.drawImage(background.getImage(), x, y, null);
            }
        }
        //Add Background Rows

        g.drawImage(apple.getImage(),apple_x,apple_y,null);
        creatSnake(g);
    }

}
