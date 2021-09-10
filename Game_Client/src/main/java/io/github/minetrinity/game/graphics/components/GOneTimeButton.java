package io.github.minetrinity.game.graphics.components;

public class GOneTimeButton extends GButton{

    public GOneTimeButton(int x, int y, int width, int height, String texture) {
        super(x, y, width, height, texture);
    }

    public GOneTimeButton(int x, int y, int width, int height, String texture, String text) {
        super(x, y, width, height, texture, text);
    }

    @Override
    public void tick() {
        super.tick();
        if(isClicked()){
            setLocked(true);
            setVisible(false);
        }
    }
}
