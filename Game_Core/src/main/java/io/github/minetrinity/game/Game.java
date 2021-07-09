package io.github.minetrinity.game;

import io.github.minetrinity.game.net.UDPWrapper;
import io.github.minetrinity.game.util.Tickable;

import java.io.File;
import java.util.ArrayList;

public class Game extends Thread{

    protected static Game instance;

    public static void main(String[] args) {
        new UDPWrapper();
        getInstance().startGame();
    }

    public static Game getInstance() {
        if(instance == null) instance = new Game();
        return instance;
    }

    protected boolean running = false;
    protected boolean started = false;

    protected double tpscap = 60.0;
    protected double actualticks;

    protected ArrayList<Tickable> tickables = new ArrayList<>();

    protected Game(){

    }

    public void run(){
        if(!running) {
            startGame();
            return;
        }

        long currenttime = System.currentTimeMillis();
        long lastttime = currenttime;
        double dtime = 0;
        double mpt = 1000 / tpscap; //millisPerFrame

        long ticks = 0;
        long timer = System.currentTimeMillis();

        while (running){
            dtime += (currenttime - lastttime) / mpt;
            lastttime = currenttime;

            if(dtime >= 1){
                tick();
                ticks++;
                dtime--;
            }

            currenttime = System.currentTimeMillis();

            if((currenttime - timer) > 1000){
                timer = currenttime;
                actualticks = ticks;
                ticks = 0;

            }
        }
    }

    protected void init(){
    }



    protected void tick(){
        for (Tickable tickable : tickables) {
            tickable.tick();
        }
    }

    //TODO: fixme
    public final void startGame(){
        if(!running && !started) {
            started = true;
            init();
            running = true;
            super.start();
        }
    }

    public final void stopGame(){
        running = false;
        System.exit(0);
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStarted() {
        return started;
    }
}
