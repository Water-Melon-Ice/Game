package io.github.minetrinity.game.graphics.components;

public class GToggleButton extends GButton{

    private boolean state = false;

    public GToggleButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void toggleState() {
        this.state = !this.state;
    }
}
