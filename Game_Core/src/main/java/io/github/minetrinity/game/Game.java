package io.github.minetrinity.game;

import io.github.minetrinity.game.time.Tickable;

import java.util.ArrayList;

public class Game extends Thread {

    protected static Game instance;

    public static void main(String[] args) {

    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    protected volatile boolean running = false;
    protected boolean started = false;

    protected double tickspersecond = 60.0;
    public volatile long actualticks;

    protected ArrayList<Tickable> tickables = new ArrayList<>();

    protected Game() {

    }

    public void run() {
        if (!running) {
            startGame();
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
                tickcounter = 0;
            }
        }
    }

    protected void init() {
    }


    protected void tick() {
        for (Tickable tickable : tickables) {
            tickable.tick();
        }
    }

    //fixme works?
    public final void startGame() {
        if (!running && !started) {
            started = true;
            init();
            running = true;
            super.start();
        }
    }

    public final void stopGame() {
        running = false;
        try {
            super.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStarted() {
        return started;
    }

    public void add(Tickable t) {
        tickables.add(t);
    }

    public void remove(Tickable t) {
        tickables.remove(t);
    }

    public double getTickspersecond() {
        return tickspersecond;
    }
}
