package io.github.minetrinity.game.file;

import io.github.minetrinity.game.graphics.TextureFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class ResourceFactory<T> {

    private static ArrayList<ResourceFactory<?>> resourceFactories = new ArrayList<>();

    static {
        registerResourceFactory(new TextureFactory());
    }

    public static void registerResourceFactory(ResourceFactory<?> factory){
        resourceFactories.add(factory);
    }

    public static void unregisterResourceFactory(ResourceFactory<?> factory){
        resourceFactories.remove(factory);
    }

    public static ArrayList<ResourceFactory<?>> getResourceFactories() {
        return resourceFactories;
    }

    public static ResourceFactory<?>[] getResourceFactories(String format){
        ArrayList<ResourceFactory<?>> rffs = new ArrayList<>();
        for(ResourceFactory<?> rf : resourceFactories){
            if(rf.isReadable(format)) rffs.add(rf);
        }
        return rffs.toArray(ResourceFactory[]::new);
    }

    public static ResourceFactory<?>[] getResourceFactories(File f){
        return getResourceFactories(getFileType(f));
    }

    public static String getFileType(File f){
        return f.getName().substring(f.getName().indexOf(".") + 1);
    }

    protected ResourceFactory(){
    }

    public abstract T read(InputStream in, String format);

    public T read(File f){
        return read(Resources.getInputstream(f), f.getName().substring(f.getName().lastIndexOf(".")+1));
    }

    public abstract boolean isReadable(String format);

}
