package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.Game;
import io.github.minetrinity.game.input.Controls;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class Window extends Frame {

    private static GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    protected static Window instance;

    public static Window getInstance() {
        if (instance == null) instance = new Window();
        return instance;
    }

    private BufferStrategy strat;

    private boolean guichangeLock = false;
    private GUI next;
    protected GUI root;

    private Window() {
        setUndecorated(true);
        setResizable(false);
        setLocation(0, 0);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().stopGame();
                getInstance().dispose();
            }
        });
        setBackground(Color.black);
        addKeyListener(Controls.getInstance());
    }

    public void setFullscreen(boolean trueFullscreen) {
        if (trueFullscreen && gdevice.isFullScreenSupported()) {
            if (!isVisible()) {
                setFullscreen(false);
                return;
            }
            gdevice.setFullScreenWindow(this);
        } else {
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
        }
    }

    public Graphics getDrawGraphics() {
        if (strat == null) {
            createBufferStrategy(2);
            strat = getBufferStrategy();
        }
        return strat.getDrawGraphics();
    }

    public void render() {
        if (root != null) {
            root.paintAll(getDrawGraphics());
            getBufferStrategy().show();
        }
    }

    public void setGUI(GUI root) {
        if (guichangeLock) {
            next = root;
            return;
        }
        if (this.root != null) {
            this.root.close();
            Game.getInstance().remove(this.root);
        }
        this.root = root;
        root.open();
        Game.getInstance().remove(root);
    }

    public void setGUIChangeLock(boolean changeLock) {
        this.guichangeLock = changeLock;
        if (!changeLock && next != null) {
            setGUI(next);
            next = null;
        }

    }

    public GUI getRoot() {
        return root;
    }
}
