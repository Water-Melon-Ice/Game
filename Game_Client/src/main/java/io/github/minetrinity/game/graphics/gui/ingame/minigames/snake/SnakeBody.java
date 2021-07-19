package io.github.minetrinity.game.graphics.gui.ingame.minigames.snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeBody {

    private List<Point> SnakeBody = new ArrayList<>();
    private Point snakeHead;

    private Image body;
    private Image head;

    private void loadImages() {

        ImageIcon iid = new ImageIcon("  ");
        body = iid.getImage();

        ImageIcon iia = new ImageIcon("  ");
        head = iia.getImage();
    }

}
