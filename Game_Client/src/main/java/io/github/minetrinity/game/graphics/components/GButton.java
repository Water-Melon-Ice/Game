package io.github.minetrinity.game.graphics.components;

import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.io.Resources;


import java.awt.*;

public class GButton extends GComponent {

    private boolean islocked;

    private String text;
    private Texture texture;


    public GButton(int x, int y, int width, int height, String texture) {
        this(x, y, width, height, texture, texture);
    }

    public GButton(int x, int y, int width, int height, String texture, String text) {
        super(x, y, width, height);
        this.texture = (Texture) Resources.getResource(texture);
        this.text = text;
    }

    @Override
    public void paint(Graphics g) {
        if (text != null)
            g.drawString(text, 0, 10);
        if (texture != null) {
            texture.paint(g);
            if (isMouseOver() && Controls.isMousePressed(1)) {
                g.drawImage(texture.getImageFillNonOpaque(new Color(64, 64, 64, 128)), 0, 0, getWidth() - 1, getHeight() - 1, null);
            } else if (isMouseOver()) {
                g.drawImage(texture.getImageFillNonOpaque(new Color(64, 64, 64, 64)), 0, 0, getWidth() - 1, getHeight() - 1, null);
            }
        }
    }

    @Override
    public void tick() {
        if (isMouseOver() && Controls.isMousePressed(1)){
            onClick();
        }
    }

    public void onClick() {

    }

    public void setLocked(boolean islocked) {
        this.islocked = islocked;
    }

    public boolean isLocked() {
        return islocked;
    }

    public String getText() {
        return text.toString();
    }

    public void setText(String text) {
        this.text = text;
    }
}