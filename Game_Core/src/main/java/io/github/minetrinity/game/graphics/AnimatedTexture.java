package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.time.Tickable;

import java.awt.*;
import java.util.ArrayList;

public class AnimatedTexture extends Texture implements Tickable {


    private int delay = 5;
    private int nextframeticks = 0;
    private int currentFrame = 0;

    private ArrayList<Image> frames = new ArrayList<>();

    public AnimatedTexture(int width, int height) {
        super(width, height);
    }

    public AnimatedTexture(int width, int height, int delay) {
        super(width, height);
        this.delay = delay;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public Image getImage() {
        return frames.get(currentFrame).getScaledInstance(width * 8, height * 8, Image.SCALE_FAST);
    }


    @Override
    public void tick() {
        if (nextframeticks >= delay) {
            nextFrame();
            nextframeticks -= delay;
        }else {
            nextframeticks++;
        }
    }

    protected void nextFrame() {
        if (currentFrame < frames.size() - 1) {
            currentFrame++;
        } else {
            currentFrame = 0;
        }
    }

    public void remove(int frame) {
        frames.remove(frame);
    }

    public void add(Image i) {
        frames.add(i);
    }

    public void put(Image i, int frame){
        frames.add(frame, i);
    }

    public void addAll(Image... images) {
        for (Image i : images) {
            add(i);
        }
    }

    public void addAll(Texture... textures) {
        for (Texture t : textures) {
            add(t.getImage());
        }
    }

    public int getFrameCount() {
        return frames.size();
    }

    /**
     delay in milliseconds between frames
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     delay in milliseconds between frames
     */
    public int getDelay() {
        return delay;
    }
}
