package io.github.minetrinity.game.file;

import java.io.File;

public abstract class Resource<T> {

    public Resource(){

    }

    public abstract T get();
    public abstract void reload();
    public abstract boolean isReloadable();

}
