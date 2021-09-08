package io.github.minetrinity.game.graphics.components;

import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.Paintable;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.time.Tickable;

import java.awt.*;

public abstract class GOverlay extends GComponent implements Paintable, Tickable {

    public GOverlay(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public GOverlay(Dimension size) {
        super(size);
    }

    public GOverlay(){
        super(Window.getInstance().getSize());

    }

    public void open() {
        if(getParent() instanceof GUI){
            GUI pt = (GUI) getParent();
            setSize(pt.getSize());
            pt.overlays++;
        }
    }

    public void close() {
        if(getParent() instanceof GUI){
            ((GUI) getParent()).overlays--;
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
