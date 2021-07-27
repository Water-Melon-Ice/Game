package io.github.minetrinity.game.graphics.gui.ingame;

import io.github.minetrinity.game.graphics.Overlay;

public class Camera extends Overlay {
/*
    private BufferedImage image;
    int ticks = 0;

    public BufferedImage areaToImage(){
        if(image == null || ticks > 5) {
            BufferedImage img = new BufferedImage(getWidth() * 16, getHeight() * 16, BufferedImage.TYPE_INT_ARGB);
            Graphics g = img.createGraphics();
            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    for (int layer = 0; layer < getLayers(); layer++) {
                        if (tiles[x][y][layer] != null) {
                            Texture tempt = Textures.getByName(tiles[x][y][layer].getTexture());
                            g.drawImage(tempt.getBufferedImage(), x * 16, y * 16, null);
                        }
                    }
                }
            }
            g.dispose();
            image = img;
            ticks = 0;
        }else {
            ticks++;
        }
        return image;
    }
*/
    public Camera() {
    }
}
