package io.github.minetrinity.game.graphics;

import java.awt.*;

public class Window extends Frame {

    private static GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    protected static Window instance;

    public static Window getInstance() {
        if(instance == null) instance = new Window();
        return instance;
    }

    private Window(){
        setUndecorated(true);
        setResizable(false);
        setLocation(0,0);
    }

    public void setFullscreen(boolean trueFullscreen){
        if(trueFullscreen && gdevice.isFullScreenSupported()){
            if(isVisible()) {
                setFullscreen(false);
                return;
            }
            gdevice.setFullScreenWindow(this);
        }else{
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
        }
    }

}
