package io.github.minetrinity.game.ingame.world;

import io.github.minetrinity.game.file.Resources;
import io.github.minetrinity.game.graphics.Texture;
import io.github.minetrinity.game.graphics.Textures;

import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class Tile {

    protected String texname;

    public Tile(String texname){
        this.texname = texname;
    }

    public String getTexture(){
        return texname;
    }

    public void setTexture(String t){
        this.texname = t;
    }
}
