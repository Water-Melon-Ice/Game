package io.github.minetrinity.game.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public abstract class ResourceIO<T> {

    protected HashMap<String, T> map = new HashMap<>();

    protected ResourceIO() {
    }

    public abstract boolean isReadable(String format);
    public abstract boolean isWriteable();

    public abstract T read(InputStream in);
    public abstract void write(OutputStream out, T obj);

    public T getByName(String key) {
        return map.get(key);
    }

    public void put(String key, T value) throws RuntimeException {
        map.put(key, value);
    }

    public void put(String key, InputStream value) throws RuntimeException {
        map.put(key, read(value));
    }

    public void release() {
        map.clear();
    }

}
