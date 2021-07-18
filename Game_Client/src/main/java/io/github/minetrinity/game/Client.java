package io.github.minetrinity.game;

import io.github.minetrinity.game.graphics.Window;
import io.github.minetrinity.game.graphics.gui.TestGui;

import java.awt.image.BufferedImage;

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
        Window.getInstance().setVisible(true);
        Window.getInstance().setFullscreen(false);
        TestGui g = new TestGui();
        tickables.add(g);
        Window.getInstance().setRoot(g);
        /*
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
        */
    }

    //TODO: THIS METHOD DISGUSTS ME, FIX AND DISTRIBUTE!
    private BufferedImage TEMPORARYMAPIMAGE(){/*
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
        }*/
        return null;
    }

    @Override
    protected void tick() {
        super.tick();
        Window.getInstance().render();
    }
}
