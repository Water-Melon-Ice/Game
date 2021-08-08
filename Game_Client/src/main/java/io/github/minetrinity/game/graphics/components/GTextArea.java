package io.github.minetrinity.game.graphics.components;

import java.awt.*;

public class GTextArea extends GComponent{

    private static Color textcolor = Color.green;

    private String text;
    private Color color;

    public GTextArea(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public GTextArea(int x, int y, int width, int height, String text, Color color) {
        super(x, y, width, height);
        this.text = text;
        this.color = color == null ? textcolor : color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.drawString(text, getWidth() / 2 - (text.length() * 5) ,getHeight() / 2);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
