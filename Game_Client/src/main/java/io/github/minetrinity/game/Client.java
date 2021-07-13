package io.github.minetrinity.game;

import io.github.minetrinity.game.file.Resource;
import io.github.minetrinity.game.graphics.AreaToImage;
import io.github.minetrinity.game.graphics.LayeredTexture;
import io.github.minetrinity.game.ingame.world.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        //Worldmap
        BufferedImage temporary = TEMPORARYMAPIMAGE();
        File out = new File("./out.png");
        try {
            FileOutputStream outs = new FileOutputStream(out);
            ImageIO.write(temporary, "png", outs);
            outs.close();
            System.out.println("done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //TODO: THIS METHOD DISGUSTS ME, FIX AND DISTRIBUTE!
    private BufferedImage TEMPORARYMAPIMAGE(){
        try {
            Stream<File> world = Resource.allFiles().stream().filter(file -> file.getName().contains("world"));
            ArrayList<BufferedImage> worlds = Resource.allImages(world.collect(Collectors.toList()));
            LayeredTexture limg = new LayeredTexture(worlds.toArray(Image[]::new));
            Resource.fillTileMap();
            Area a = Area.from(limg);
            AreaToImage.fillTileImageMap();
            return AreaToImage.toImage(a, -1);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void tick() {
        super.tick();
    }
}
