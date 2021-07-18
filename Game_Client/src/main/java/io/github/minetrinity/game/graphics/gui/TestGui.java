package io.github.minetrinity.game.graphics.gui;

import io.github.minetrinity.game.graphics.AnimatedTexture;
import io.github.minetrinity.game.graphics.GUI;
import io.github.minetrinity.game.graphics.Textures;

import java.awt.*;
import java.io.File;

public class TestGui extends GUI {

    AnimatedTexture tex;

    @Override
    public void open() {
        tex = Textures.readGif(Textures.getInputstream(new File("../defaultresources/images/tiles/Wasser.gif")));
    }

    @Override
    public void close() {

    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Graphics g2 = g.create(x * tex.getHeight() * 8, y * tex.getWidth() * 8, tex.getWidth() * 8, tex.getHeight() * 8);
                tex.paint(g2);
            }
        }
    }

    @Override
    public void tick() {
        tex.tick();

    }
}
