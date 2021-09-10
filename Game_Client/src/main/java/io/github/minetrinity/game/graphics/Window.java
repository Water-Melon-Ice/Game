package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.Client;
import io.github.minetrinity.game.Game;
import io.github.minetrinity.game.graphics.gui.menu.GUIMenu;
import io.github.minetrinity.game.input.Controls;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {

    private static boolean disposelistener = false;

    private static GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    protected static Window instance;

    public static boolean showFPS = true;

    public static Window getInstance() {
        if (instance == null) instance = new Window();
        return instance;
    }

    public static void init() {
        getInstance().setVisible(true);
        getInstance().setFullscreen(true);

        getInstance().setGUI(new GUIMenu());
    }


    private Frame frame;


    private boolean guichangeLock = false;
    private GUI next;
    protected GUI root;

    private Color defaultBackground = Color.black;

    private Window() {
        frame = new Frame();
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setLocation(0, 0);
        frame.setBackground(Color.black);
        frame.addKeyListener(Controls.getInstance());
        frame.addMouseListener(Controls.getInstance());
        frame.setFocusable(true);
        if (disposelistener)
            frame.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Game.getInstance().stopGame();
                    frame.dispose();
                }
            });
    }

    public void setFullscreen(boolean trueFullscreen) {
        if (trueFullscreen && gdevice.isFullScreenSupported()) {
            if (!frame.isVisible()) {
                setFullscreen(false);
                return;
            }
            gdevice.setFullScreenWindow(frame);
        } else {
            frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        }
    }

    public Graphics getDrawGraphics() {
        if (frame.getBufferStrategy() == null) {
            frame.createBufferStrategy(2);
        }
        return frame.getBufferStrategy().getDrawGraphics();
    }

    public void render() {
        if (!guichangeLock && next != null) {
            setGUI(next);
            next = null;
        }
        if (root != null) {
            Graphics2D graphics = (Graphics2D) getDrawGraphics();
            graphics.setBackground(defaultBackground);
            graphics.clearRect(0, 0, frame.getWidth(), frame.getHeight());
            root.paintAll(graphics);

            if (showFPS)
                drawFPS(graphics);

            frame.getBufferStrategy().show();
        }
    }

    public void setGUI(GUI root) {
        if (guichangeLock) {
            next = root;
            return;
        }
        if (this.root != null) {
            this.root.close();
        }
        this.root = root;
        root.setSize(this.frame.getSize());
        root.open();
    }

    public void setGUIChangeLock(boolean changeLock) {
        this.guichangeLock = changeLock;
    }

    public GUI getRoot() {
        return root;
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
        frame.createBufferStrategy(2);
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    public int getX() {
        return frame.getX();
    }

    public int getY() {
        return frame.getY();
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public Frame getFrame() {
        return frame;
    }

    private void drawFPS(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 16, 16);
        g.setColor(Color.white);
        g.drawString("" + Client.getInstance().getActualticks(), 0, 10);
    }
}
