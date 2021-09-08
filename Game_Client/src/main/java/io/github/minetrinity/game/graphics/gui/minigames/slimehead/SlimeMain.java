package io.github.minetrinity.game.graphics.gui.minigames.slimehead;


import io.github.minetrinity.game.graphics.gui.minigames.MinigameOverlay;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.io.Resources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SlimeMain extends MinigameOverlay {

    private static final int WIDTH = 512;
    private static final int HEIGHT = WIDTH;

    private int Bong_x;
    private int Bong_y;

    private int bomb_x;
    private int bomb_y;

    private int score;

    private int tick;

    private List<Point> bombs = new ArrayList<>();
    private Point slime = new Point(WIDTH / 2, HEIGHT /2);

    Texture background2 = (Texture) Resources.getResource("SnakeBackgroundLightGreen.png");
    Texture background = (Texture) Resources.getResource("SnakeBackgroundDarkGreen.png");
    Texture Bong = (Texture) Resources.getResource("BongImage.png");
    Texture slimeImage = (Texture) Resources.getResource("SlimeImage.png");
    Texture bombImage = (Texture) Resources.getResource("BombImage.png");


    private void placeBong() {

        int a = (int) (Math.random() * WIDTH);
        while (a % 16 != 0) {
            a = (int) (Math.random() * WIDTH );
        }
        Bong_x = a;

        a = (int) (Math.random() * HEIGHT);
        while (a % 16 != 0) {
            a = (int) (Math.random() * HEIGHT);
        }
        Bong_y = a;

        for (Point i: bombs) {
            if ((Bong_x == i.x) && (Bong_y == i.y)) {
                placeBong();
            }
        }

        if ((Bong_x == 0) || (Bong_x == WIDTH - 1 * 16) || (Bong_y == 0) || (Bong_y == HEIGHT - 1 * 16)) {
            placeBong();
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

        for (Point i: bombs) {
            if ((bomb_x == i.x) && (bomb_y == i.y)) {
                return placebomb();
            }
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

        if ((slime.x == Bong_x) && (slime.y == Bong_y)) {

            bombs.add(placebomb());
            bombs.add(placebomb());
            placeBong();

            score = score + 1;
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
        bombs = new ArrayList<>();
        score = 0;
        tick = 0;
        slime = new Point(WIDTH / 2, HEIGHT /2);


        placeBong();
        score = 0;
        for (int i = 0; i < 5; i++) {
            bombs.add(placebomb());
        }
        placeBong();

    }


    @Override
    public int closeMinigame() {
        return score;
    }

    @Override
    public void paint(Graphics g) { //render slime and background to Graphics here.
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
        g.drawImage(Bong.getImage(), Bong_x, Bong_y,null);
        paintbomb(g);
        g.setColor(new Color(8, 140, 210));
        g.drawString("Score: " + score, HEIGHT, 16);
    }

    @Override
    public void tick() {
        eaten();
        if (tick % 2 == 0) { //slowing down Slime
            move();
        }
        collision();
        tick = tick + 1;
    }
}