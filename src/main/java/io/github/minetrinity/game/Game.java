package io.github.minetrinity.game;

import io.github.minetrinity.game.graphics.Window;

public class Game {

    private static Game instance;

    public static void main(String[] args) {
        new Game().start();
    }

    public static Game getInstance() {
        if(instance == null) instance = new Game();
        return instance;
    }

    protected boolean running = false;
    protected boolean started = false;

    protected double fpscap = 60.0;
    protected double actualfps;

    protected Game(){

    }

    protected void run(){
        long currenttime = System.currentTimeMillis();
        long lastttime = currenttime;
        double dtime = 0;
        double mpf = 1000 / fpscap; //millisPerFrame

        long fps = 0;
        long timer = System.currentTimeMillis();

        while (running){
            dtime += (currenttime - lastttime) / mpf;
            lastttime = currenttime;

            if(dtime >= 1){
                tick();
                render();
                fps++;
                dtime--;
            }

            currenttime = System.currentTimeMillis();

            if((currenttime - timer) > 1000){
                timer = currenttime;
                actualfps = fps;
                fps = 0;

            }
        }
    }

    protected void init(){
        Window.getInstance().setFullscreen(true);
        Window.getInstance().setVisible(true);
    }

    protected void render(){

    }

    protected void tick(){

    }

    public void start(){
        if(!running && !started) {
            started = true;
            init();
            running = true;
            run();
        }
    }

    public void stop(){
        running = false;
        System.exit(0);
    }


}
