package io.github.minetrinity.game.graphics.gui.minigames.snake;


import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.gui.minigames.MinigameOverlay;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.io.Resources;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SnakeMain extends MinigameOverlay {

    private static final int WIDTH = 512;
    private static final int HEIGHT = WIDTH;
    private static final int ROW = WIDTH / 16;
    private static final int ALL_SQUARES = WIDTH * HEIGHT;


    private final int[] x = new int[ALL_SQUARES];
    private final int[] y = new int[ALL_SQUARES];

    private int apple_x;
    private int apple_y;

    private boolean W = false;
    private boolean S = false;
    private boolean A = false;
    private boolean D = false;

    private boolean leftDirection = false;
    private boolean  rightDirection = false;
    private boolean  upDirection = false;
    private boolean  downDirection = false;

    boolean eaten = false;

    private int score;
    private int tick;

    private final List<Point> snakebody = new ArrayList<>();
    private Point snakeHead = new Point(WIDTH / 2, HEIGHT /2);

    Texture background2 = (Texture) Resources.getResource("SnakeBackgroundLightGreen.png");
    Texture background = (Texture) Resources.getResource("SnakeBackgroundDarkGreen.png");
    Texture apple = (Texture) Resources.getResource("Pixel-apple.png");
    Texture snakeHeadImage = (Texture) Resources.getResource("snakehead.png");
    Texture snakeBodyImage = (Texture) Resources.getResource("snakebody.png");
    Texture snakeTailImage = (Texture) Resources.getResource("snakebutt.png");


    private void placeApple() {

        int a = (int) (Math.random() * WIDTH);
        while (a % 16 != 0) {
            a = (int) (Math.random() * WIDTH );
        }
        apple_x = a;

        a = (int) (Math.random() * HEIGHT);
        while (a % 16 != 0) {
            a = (int) (Math.random() * HEIGHT);
        }
        apple_y = a;
    }

    private void creatSnake(Graphics g) {
        for (int i = 0; i < snakebody.size() - 1; i++) {
            g.drawImage(snakeBodyImage.getImage(), snakebody.get(i).x , snakebody.get(i).y , null);
        }
        g.drawImage(snakeTailImage.getImage(), snakebody.get(snakebody.size() - 1).x , snakebody.get(snakebody.size() - 1).y , null);
        g.drawImage(snakeHeadImage.getImage(), snakeHead.x, snakeHead.y, null);

    }

    private void eaten() {

        if ((snakeHead.x == apple_x) && (snakeHead.y == apple_y)) {

            snakebody.add(snakebody.get(snakebody.size() - 1));
            placeApple();
            eaten = true;
            score = score +1;
        }
    }

    private void move() {

        int sub = 0;
        if(eaten) sub = 1;

        for (int z = 1; z < snakebody.size() - sub; z++) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        W = Controls.isKeyPressed('W');
        S = Controls.isKeyPressed('S');
        A = Controls.isKeyPressed('A');
        D = Controls.isKeyPressed('D');

        if (W == true) {
            upDirection = true;
            downDirection = false;
            leftDirection = false;
            rightDirection = false;
        }

        if (S == true) {
            upDirection = false;
            downDirection = true;
            leftDirection = false;
            rightDirection = false;
        }

        if (A == true) {
            upDirection = false;
            downDirection = false;
            leftDirection = true;
            rightDirection = false;
        }

        if (D == true) {
            upDirection = false;
            downDirection = false;
            leftDirection = false;
            rightDirection = true;
        }

    }

    private void automove() {

        if (upDirection == true) {
            snakeHead.y = snakeHead.y - 16;
        }

        if (downDirection == true) {
            snakeHead.y = snakeHead.y + 16;
        }

        if (leftDirection == true) {
            snakeHead.x = snakeHead.x - 16;
        }

        if (rightDirection == true) {
            snakeHead.x = snakeHead.x + 16;
        }

    }


    private void collision() {
        if ((snakeHead.x > WIDTH - 1) || (snakeHead.y > HEIGHT - 1) || (snakeHead.x < 0 ) || (snakeHead.y < 0)) {
            die();
        }
    }

    @Override
    public void open() { //called on opening of the GUI
        snakebody.add(new Point(snakeHead));
        placeApple();

    }


    @Override
    public int closeMinigame() {
        return score;
    }

    @Override
    public void paint(Graphics g) { //render snake and background to Graphics here.
        super.paint(g);
        for(int x = 0; x < WIDTH / 16; x++) {
            for(int y = 0; y < HEIGHT / 16; y++){
                if ((x + y) %2 == 1) {
                    g.drawImage(background.getImage(), x * 16, y * 16, null);
                }
                else {
                    g.drawImage(background2.getImage(), x * 16, y * 16, null);
                }
            }
        }

        g.drawImage(apple.getImage(),apple_x,apple_y,null);
        creatSnake(g);
        g.setColor(new Color(8, 140, 210));
        g.drawString("Score: " + score, HEIGHT, 16);
    }

    @Override
    public void tick() {
        eaten();
        if (tick % 10 == 0) { //slowing down the Snake
            move();
            automove();
        }
        collision();
        tick = tick + 1;
    }
}
//Glider Game: snakeHead.x += 5; (Space