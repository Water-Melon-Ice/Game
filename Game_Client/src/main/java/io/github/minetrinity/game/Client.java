package io.github.minetrinity.game;

public class Client extends Game{

    protected static Client instance;

    public static Client getInstance() {
        if (instance == null) instance = new Client();
        return instance;
    }

    public static void main(String[] args) {
        getInstance().startGame();
    }

    public Client(){

    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void tick() {
        super.tick();
    }
}
