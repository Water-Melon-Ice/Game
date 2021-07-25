package io.github.minetrinity.game.file;

import java.io.File;
import java.io.InputStream;

public abstract class ResourceFactory<T> {

    protected ResourceFactory(){
    }

    public abstract Resource<T> read(InputStream in);

    public Resource<T> read(File f){
        return read(Resources.getInputstream(f));
    }

    public abstract boolean isReadable(String format);

}
