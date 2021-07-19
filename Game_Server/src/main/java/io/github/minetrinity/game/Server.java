package io.github.minetrinity.game;

public class Server extends Game {

    protected static Server instance;

    public static Server getInstance() {
        if (instance == null) instance = new Server();
        return instance;
    }

    public static void main(String[] args) {
        getInstance().start();
    }

    public Server(){

    }

    @Override
    protected void init() {
        super.init();
    }
}
