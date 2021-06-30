package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().stop();
            }
        });
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
