package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.time.Tickable;

import java.awt.*;

public class AnimatedTexture extends Texture implements Tickable {

    private int ticksperframe = 0;
    private int ct = 0;
    private int cf = 0;

    private LayeredTexture lt;

    public AnimatedTexture(String name, LayeredTexture lt) {
        super(name, null);
    }

    public AnimatedTexture(String name, Texture... textures) {
        super(name, null);
    }



    @Override
    public Image getImage() {
        return lt.get(cf);
    }


    @Override
    public void tick() {
        if(ct == ticksperframe){
            nextFrame();
            ct = 0;
        }else {
            ct++;
        }
    }

    protected void nextFrame(){
        cf++;
    }
}
