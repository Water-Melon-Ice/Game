package io.github.minetrinity.game.graphics.gui.ingame.minigames.slimehead;


import io.github.minetrinity.game.graphics.gui.ingame.minigames.MinigameGUI;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.io.Resources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SlimeMain extends MinigameGUI {

    private static final int WIDTH = 512;
    private static final int HEIGHT = WIDTH;
    private static final int ROW = WIDTH / 16;
    private static final int ALL_SQUARES = WIDTH * HEIGHT;


    private final int[] x = new int[ALL_SQUARES];
    private final int[] y = new int[ALL_SQUARES];

    private int apple_x;
    private int apple_y;

    private int bomb_x;
    private int bomb_y;

    private int squares;

    boolean eaten = false;

    private final List<Point> bombs = new ArrayList<>();
    private final Point slime = new Point(WIDTH / 2, HEIGHT /2);

    Texture background2 = (Texture) Resources.getResource("SnakeBackgroundLightGreen.png");
    Texture background = (Texture) Resources.getResource("SnakeBackgroundDarkGreen.png");
    Texture apple = (Texture) Resources.getResource("Pixel-apple.png");
    Texture slimeImage = (Texture) Resources.getResource("snakehead.png");
    Texture bombImage = (Texture) Resources.getResource("snakebody.png");


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

        for (Point i: bombs) {
            if ((apple_x == i.x) && (apple_y == i.y)) {
                placeApple();
            }
        }

        if ((apple_x == 0) || (apple_x == WIDTH - 1) || (apple_y == 0) || (apple_y == HEIGHT - 1)) {
            placeApple();
        }
    }

    private Point placebomb() {
        int a = (int) (Math.random() * WIDTH);
        while (a % 16 != 0) {
            a = (int) (Math.random() * WIDTH );
        }
        bomb_x = a;

        a = (int) (Math.random() * HEIGHT);
        while (a % 16 != 0) {
            a = (int) (Math.random() * HEIGHT);
        }
        bomb_y = a;

        if ((bomb_x == slime.x) && (bomb_y == slime.y)) {
            return placebomb();
        }

        return new Point (bomb_x, bomb_y);

    }

    private void paintbomb(Graphics g) {
        for (int i = 0; i < bombs.size(); i++) {
            g.drawImage(bombImage.getImage(), bombs.get(i).x , bombs.get(i).y , null);
        }
        g.drawImage(slimeImage.getImage(), slime.x, slime.y, null);

    }

    private void eaten() {

        if ((slime.x == apple_x) && (slime.y == apple_y)) {

            bombs.add(placebomb());
            placeApple();
        }
    }

    private void move() {
        slime.x += Controls.getX() * 16;
        slime.y += Controls.getY() * 16;
    }

    private void collision() {
        if ((slime.x > WIDTH - 1) || (slime.y > HEIGHT - 1) || (slime.x < 0 ) || (slime.y < 0)) {
            die();
        }
        for (Point i: bombs){
            if ((slime.x == i.x) && (slime.y == i.y)) {
                die();
            }
        }
    }

    @Override
    public void open() { //called on opening of the GUI
        for (int i = 0; i < 5; i++) {
            bombs.add(placebomb());
        }
        placeApple();

    }


    @Override
    public int closeMinigame() {
        return 0;
    }

    @Override
    public void paint(Graphics g) { //render snake and background to Graphics here.

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
        paintbomb(g);
    }

    @Override
    public void tick() {
        eaten();
        move();
        collision();
    }
}