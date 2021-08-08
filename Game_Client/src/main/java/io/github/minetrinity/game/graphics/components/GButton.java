package io.github.minetrinity.game.graphics.components;

import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.components.GComponent;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.load.ResourceFactory;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class GButton extends GComponent {

    private boolean islocked;

    private String text;
    private String texname;
    private Texture texture;


    public GButton(int x, int y, int width, int height, String text) {
        this(x, y, width, height, text, null);
    }

    public GButton(int x, int y, int width, int height, String text, String texname) {
        super(x, y, width, height);
        this.texname = texname;
        this.text = text;
        initialize();
    }

    @Override
    public void initialize() {
        texture = (Texture) ResourceFactory.getResourceFactories("png")[0].getByName(texname);
    }

    @Override
    public void paint(Graphics g) {
        if (texture != null)
            texture.paint(g);
        if (text != null)
            g.drawString(text, 0, 0);
        if (isMouseOver() && Controls.isMousePressed(1)) {
            g.drawImage(texture.getImageFillNonOpaque(new Color(64, 64, 64, 128)), 0, 0, getWidth() - 1, getHeight() - 1, null);
        }else if(isMouseOver()){
            g.drawImage(texture.getImageFillNonOpaque(new Color(64, 64, 64, 64)), 0, 0, getWidth() - 1, getHeight() - 1, null);
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

    public String getTexture() {
        return texname;
    }
}