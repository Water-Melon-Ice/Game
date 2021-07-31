package io.github.minetrinity.game.load;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ResourceFactory<T> {

    private static ArrayList<ResourceFactory<?>> resourceFactories = new ArrayList<>();

    public static void registerResourceFactory(ResourceFactory<?> factory) {
        resourceFactories.add(factory);
    }

    public static void unregisterResourceFactory(ResourceFactory<?> factory) {
        resourceFactories.remove(factory);
    }

    public static ArrayList<ResourceFactory<?>> getResourceFactories() {
        return resourceFactories;
    }

    public static ResourceFactory<?>[] getResourceFactories(String format) {
        ArrayList<ResourceFactory<?>> rffs = new ArrayList<>();
        for (ResourceFactory<?> rf : resourceFactories) {
            if (rf.isReadable(format)) rffs.add(rf);
        }
        return rffs.toArray(ResourceFactory[]::new);
    }

    public static ResourceFactory<?>[] getResourceFactories(File f) {
        return getResourceFactories(Resources.getFileFormat(f));
    }

    protected HashMap<String, T> map = new HashMap<>();

    public abstract boolean isReadable(String format);

    protected ResourceFactory() {
    }

    public abstract T read(InputStream in, String format);

    public T read(File f) {
        return read(Resources.getInputstream(f), f.getName().substring(f.getName().lastIndexOf(".") + 1));
    }

    public T getByName(String key) {
        return map.get(key);
    }

    public void put(String key, T value) throws RuntimeException {
        map.put(key, value);
    }

    public void put(File f) {
        if (isReadable(Resources.getFileFormat(f)))
            put(f.getName(), read(f));
    }

    public void putAll(File[] files) {
        for (File f : files) {
            put(f);
        }
    }

    public void release() {
        map.clear();
    }

}
