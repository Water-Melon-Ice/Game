package io.github.minetrinity.game;

import io.github.minetrinity.game.ingame.world.World;
import io.github.minetrinity.game.time.Tickable;

import java.util.ArrayList;

public class Game {

    protected static Game instance;

    public static void main(String[] args) {

    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    protected volatile boolean running = false;
    protected volatile boolean started = false;

    public long startTime;

    protected ArrayList<Tickable> tickables = new ArrayList<>();

    protected Game() {

    }

    public final void startGame() {
        if (!running && !started) {
            started = true;
            startTime = System.currentTimeMillis();
            init();
            running = true;
            start();
        }
    }

    public final void stopGame() {
        running = false;
        startTime = -1;
        stop();
    }

    protected void tick() {
        for (Tickable tickable : tickables) {
            tickable.tick();
        }
    }

    protected void init() {
    }

    protected void run() {
    }

    protected void start() {
    }

    protected void stop() {
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

}
