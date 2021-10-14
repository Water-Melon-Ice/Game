package io.github.minetrinity.game.graphics.gui.minigames.snake;


import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.gui.minigames.MinigameOverlay;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.io.Resources;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SnakeMain extends MinigameOverlay {

        private static final int WIDTH = 512;
        private static final int HEIGHT = WIDTH;

        private int apple_x;
        private int apple_y;

        private boolean W = false;
        private boolean S = false;
        private boolean A = false;
        private boolean D = false;

        private boolean leftDirection = false;
        private boolean  rightDirection = true;
        private boolean  upDirection = false;
        private boolean  downDirection = false;

        boolean eaten = false;

        private int score;
        private int tick;

        private final List<Point> snakebody = new ArrayList<>();

        Texture background2 = (Texture) Resources.getResource("SnakeBackgroundLightGreen.png");
        Texture background = (Texture) Resources.getResource("SnakeBackgroundDarkGreen.png");
        Texture apple = (Texture) Resources.getResource("Pixel-apple.png");
        Texture snakeHeadImageup = (Texture) Resources.getResource("snakeheadup.png");
        Texture snakeHeadImagedown = (Texture) Resources.getResource("snakeheaddown.png");
        Texture snakeHeadImageleft = (Texture) Resources.getResource("snakeheadleft.png");
        Texture snakeHeadImageright = (Texture) Resources.getResource("snakeheadright.png");
        Texture snakeBodyImage = (Texture) Resources.getResource("snakebody.png");


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
            for (int i = 2; i < snakebody.size() - 1; i++) {
                g.drawImage(snakeBodyImage.getImage(), snakebody.get(i).x , snakebody.get(i).y , null);
            }
        }

        private void eaten() {

            if ((snakebody.get(0).x == apple_x) && (snakebody.get(0).y == apple_y)) {

                snakebody.add(new Point(snakebody.get(snakebody.size() - 1)));
                snakebody.add(new Point(snakebody.get(snakebody.size() - 1)));
                placeApple();
                eaten = true;
                score = score +1;
            }
        }

        private void move() {

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
                snakebody.get(0).y = snakebody.get(0).y - 16;
            }

            if (downDirection == true) {
                snakebody.get(0).y = snakebody.get(0).y + 16;
            }

            if (leftDirection == true) {
                snakebody.get(0).x = snakebody.get(0).x - 16;
            }

            if (rightDirection == true) {
                snakebody.get(0).x = snakebody.get(0).x + 16;
            }
        }

        private void followSnake() {

            for (int i = snakebody.size() - 1; i > 0; i--) {
                snakebody.get(i).x = snakebody.get(i-1).x;
                snakebody.get(i).y = snakebody.get(i-1).y;
            }
        }

        private void rotateSnake(Graphics g) {
            if (rightDirection == true) {
                g.drawImage(snakeHeadImageright.getImage(), snakebody.get(0).x, snakebody.get(0).y, null);
            }

            if (leftDirection == true) {
                g.drawImage(snakeHeadImageleft.getImage(), snakebody.get(0).x, snakebody.get(0).y, null);
            }

            if (upDirection == true) {
                g.drawImage(snakeHeadImageup.getImage(), snakebody.get(0).x, snakebody.get(0).y, null);
            }

            if (downDirection == true) {
                g.drawImage(snakeHeadImagedown.getImage(), snakebody.get(0).x, snakebody.get(0).y, null);
            }
        }


        private void collision() {
            if ((snakebody.get(0).x > WIDTH - 1) || (snakebody.get(0).y > HEIGHT - 1) || (snakebody.get(0).x < 0 ) || (snakebody.get(0).y < 0)) {
                die();
            }

            for (int i = 5; i < snakebody.size(); i++){
                if ((snakebody.get(0).x == snakebody.get(i).x) && (snakebody.get(0).y == snakebody.get(i).y)){
                    die();
                }
            }
        }

        @Override
        public void open() { //called on opening of the GUI
            snakebody.add(new Point(WIDTH / 2, HEIGHT / 2));
            snakebody.add(new Point(snakebody.get(snakebody.size() - 1)));
            snakebody.add(new Point(snakebody.get(snakebody.size() - 1)));
            snakebody.add(new Point(snakebody.get(snakebody.size() - 1)));
            snakebody.add(new Point(snakebody.get(snakebody.size() - 1)));

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
            rotateSnake(g);
            g.setColor(new Color(8, 140, 210));
            g.drawString("Score: " + score, HEIGHT, 16);
            g.setColor(new Color(8, 140, 210));
            g.drawString("Snake: " + ((snakebody.size()-1)/2), HEIGHT, 32);
        }

        @Override
        public void tick() {
            eaten();
            if (tick % 10 == 0) { //slowing down the Snake
                move();
                followSnake();
                collision();
                automove();;
                followSnake();
                collision();
            }
            tick = tick + 1;
        }
    }
