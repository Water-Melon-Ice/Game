package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.Game;
import io.github.minetrinity.game.graphics.gui.GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Window extends Frame {

    private static GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    protected static Window instance;

    public static Window getInstance() {
        if(instance == null) instance = new Window();
        return instance;
    }

    private BufferStrategy strat;

    protected GUI root;

    private Window(){
        setUndecorated(true);
        setResizable(false);
        setLocation(0,0);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().stopGame();
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

    public Graphics getDrawGraphics(){
        if(strat == null) {
            createBufferStrategy(2);
            strat = getBufferStrategy();
        }
        return strat.getDrawGraphics();
    }

    public void render(){
        root.paintAll(getDrawGraphics());
        getBufferStrategy().show();
    }

    public void setRoot(GUI root) {
        if (this.root != null) {
            this.root.close();
        }
        this.root = root;
        root.open();
    }

    public GUI getRoot() {
        return root;
    }
}
