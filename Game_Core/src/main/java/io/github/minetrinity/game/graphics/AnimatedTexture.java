package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.time.Tickable;

import java.awt.*;
import java.util.ArrayList;

public class AnimatedTexture extends Texture implements Tickable {

    private int ticksperframe = 0;
    private int nextframeticks = 0;
    private int currentFrame = 0;

    private ArrayList<Image> frames = new ArrayList<>();

    public AnimatedTexture(Image... images) {

    }

    public AnimatedTexture(Texture... textures) {

    }



    @Override
    public Image getImage() {
        return frames.get(currentFrame);
    }


    @Override
    public void tick() {
        if(nextframeticks == ticksperframe){
            nextFrame();
            nextframeticks = 0;
        }else {
            nextframeticks++;
        }
    }

    protected void nextFrame(){
        currentFrame++;
    }

    public void remove(int frame){
        frames.remove(frame);
    }

    public void add(Image i) {
        frames.add(i);
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
}
