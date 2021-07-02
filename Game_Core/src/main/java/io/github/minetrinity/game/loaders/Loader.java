package io.github.minetrinity.game.loaders;

import java.io.InputStream;

public abstract class Loader<T>{

    protected InputStream in;

    public Loader(InputStream in){
        this.in = in;
    }

    public abstract T load();

    public InputStream getIn() {
        return in;
    }

    public boolean isLoadable(){
        return true;
    }

    public boolean isCorrectlyLoaded(){
        return true;
    }


}
