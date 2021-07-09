package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.ingame.world.Area;
import io.github.minetrinity.game.ingame.world.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AreaToImage {



    public static Image toImage(Area a, int tilesize){
        BufferedImage img = new BufferedImage(a.getWidth(), a.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        Image temp;
        int twidth, theight;
        if(tilesize == 0) tilesize = 64;

        for(int x = 0; x < a.getWidth(); x++){
            for(int y = 0; y < a.getHeight(); y++){
                for(int l = 0; l < a.getLayers(); l++){
                    temp = getImage(a.getTile(x,y,l));
                    twidth = temp.getWidth(null);
                    theight = temp.getHeight(null);
                    if(temp.getWidth(null) > tilesize || temp.getHeight(null) > tilesize){
                        return toImage(a, twidth > theight ? twidth : theight);
                    }else{
                        temp = temp.getScaledInstance(twidth, theight, BufferedImage.SCALE_DEFAULT);
                        g.drawImage(temp, x * tilesize, y * tilesize, null);
                    }
                }
            }
        }
        return img;
    }

    public static Image getImage(Tile t){
        if(t == null) return new BufferedImage(0,0,BufferedImage.TYPE_INT_ARGB);
        return null;
    }

}
