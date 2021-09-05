package io.github.minetrinity.game.graphics.gui.minigames.snake;

import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.io.Resources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeBody {

    private List<Point> SnakeBody = new ArrayList<>();
    private Point snakeHead;

    Texture body = (Texture) Resources.getResource("");
    Texture head = (Texture) Resources.getResource("");

}
