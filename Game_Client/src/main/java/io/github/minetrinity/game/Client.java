package io.github.minetrinity.game;

import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.gui.TestGui;
import io.github.minetrinity.game.graphics.gui.ingame.minigames.snake.SnakeMain;
import io.github.minetrinity.game.ingame.Player;
import io.github.minetrinity.game.load.Resources;

public class Client extends Game {

    public static Client getInstance() {
        if (instance == null) instance = new Client();
        return (Client) instance;
    }

    public static void main(String[] args) {
        getInstance().startGame();
    }

    private volatile Thread thread;

    protected double tickspersecond = 60.0;
    protected volatile long actualticks;

    protected Player p;

    public Client() {
    }

    @Override
    protected void init() {
        super.init();

        Resources.processAllFiles(Resources.walk(Resources.defaultResPath + Resources.globalPath, Integer.MAX_VALUE, true));

        Window.getInstance().setVisible(true);
        Window.getInstance().getFrame().setSize(1920, 1080);
        Window.init();

    }

    @Override
    protected void tick() {
        super.tick();
        Window.getInstance().getRoot().tickAll();
    }

    protected void paint() {
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

        long currenttime = startTime;
        long lasttime = currenttime;
        long dtime;                                     //timedifference
        double mpt = 1000 / tickspersecond;             //millisPerTick

        long tickcounter = 0;                           //counts ticks in every second
        long ticktimer = startTime;    //counts every second

        while (running) {
            dtime = currenttime - lasttime;

            if (dtime >= mpt) {
                tickcounter++;
                tick();
                paint();
                lasttime = currenttime;
            }

            currenttime = System.currentTimeMillis();

            if ((currenttime - ticktimer) > 1000) {
                ticktimer = currenttime;
                actualticks = tickcounter;
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
            if (thread != null && thread.isAlive())
                thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getActualticks() {
        return actualticks;
    }

    public Player getPlayer() {
        return p;
    }
}
