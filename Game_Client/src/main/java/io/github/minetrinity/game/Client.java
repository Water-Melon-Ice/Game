package io.github.minetrinity.game;

import io.github.minetrinity.game.file.Resources;
import io.github.minetrinity.game.graphics.*;
import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.gui.TestGui;
import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Client extends Game{

    public static Client getInstance() {
        if (instance == null) instance = new Client();
        return (Client) instance;
    }

    public static void main(String[] args) {
        getInstance().startGame();
    }

    private Thread thread;

    protected double tickspersecond = 60.0;
    protected volatile long actualticks;

    protected Runnable callback;

    public Client(){
    }

    @Override
    protected void init() {
        super.init();
        Window.getInstance().setVisible(true);
        Window.getInstance().setFullscreen(false);

        Resources.processFiles(Resources.allDefaultFiles());
        Tiles.fillTileMap();

        GUI tg = new TestGui();
        Window.getInstance().setRoot(tg);
        tickables.add(tg);
    }

    @Override
    protected void tick() {
        super.tick();
        paint();
    }

    protected void paint(){
        Window.getInstance().render();
    }

    @Override
    /**
     * Skips excess calls
     */
    protected void run() {
        if (!running) {
            thread.start();
            return;
        }

        long currenttime = System.currentTimeMillis();
        long lasttime = currenttime;
        long dtime;                                     //timedifference
        double mpt = 1000 / tickspersecond;             //millisPerTick

        long tickcounter = 0;                           //counts ticks in every second
        long ticktimer = System.currentTimeMillis();    //counts every second

        while (running) {
            dtime = currenttime - lasttime;

            if (dtime >= mpt) {
                tickcounter++;
                tick();
                lasttime = currenttime;
            }

            currenttime = System.currentTimeMillis();

            if ((currenttime - ticktimer) > 1000) {
                ticktimer = currenttime;
                actualticks = tickcounter;
                System.out.println(actualticks);
                tickcounter = 0;
            }
        }
    }

    @Override
    protected void start() {
        thread = new Thread(this::run);
        running = true;
        thread.start();
    }

    @Override
    protected void stop() {
        try {
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
