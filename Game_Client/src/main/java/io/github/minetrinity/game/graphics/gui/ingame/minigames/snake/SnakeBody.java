package io.github.minetrinity.game.graphics.gui.ingame.minigames.snake;

import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.load.ResourceFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeBody {

    private List<Point> SnakeBody = new ArrayList<>();
    private Point snakeHead;

    Texture body = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName("");
    Texture head = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName("");

}
