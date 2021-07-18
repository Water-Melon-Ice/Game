package io.github.minetrinity.game.graphics;


import io.github.minetrinity.game.file.Resource;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.InvalidKeyException;
import java.util.HashMap;

public class Textures {

    public static Color transparent = new Color(0, 0, 0, 0);

    protected static HashMap<String, Texture> texturemap = new HashMap<>();

    public static Texture getByName(String key) {
        return texturemap.get(key);
    }

    public static void put(String key, Texture value) throws InvalidKeyException {
        if (texturemap.containsKey(key)) throw new InvalidKeyException("Key already exists!");
        texturemap.put(key, value);
    }

    public static Texture[] wrap(Image... images) {
        Texture[] textures = new Texture[images.length];
        for (int i = 0; i < images.length; i++) {
            Texture t = new Texture();
            t.setImage(images[i]);
            textures[i] = t;
        }
        return textures;
    }

    public static BufferedImage toBufferedImage(Image i) {
        if (i instanceof BufferedImage) {
            return (BufferedImage) i;
        }
        BufferedImage bimg = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimg.createGraphics();
        bGr.drawImage(i, 0, 0, null);
        bGr.dispose();
        return bimg;
    }

    public static InputStream getInputstream(File f) {
        return Resource.getInputstream(f);
    }

    public static Texture read(InputStream in) {
        return null;
    }

    public static Texture readTexture(InputStream in) {
        Texture t = new Texture();
        BufferedImage img = null;
        try {
            img = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        t.setImage(img);
        return t;
    }

    public static AnimatedTexture readGif(InputStream in) {
        AnimatedTexture tex = null;

        ImageReader reader = ImageIO.getImageReadersByFormatName("GIF").next();
        ImageInputStream imgin;
        try {
            imgin = ImageIO.createImageInputStream(in);
            reader.setInput(imgin);
            tex = new AnimatedTexture(reader.getWidth(0), reader.getHeight(0));
            for (int i = 0; i < reader.getNumImages(true); i++) {
                tex.add(reader.read(i));
            }

            IIOMetadata imageMetaData = null;
            imageMetaData = reader.getImageMetadata(0);
            String metaFormatName = imageMetaData.getNativeMetadataFormatName();
            IIOMetadataNode root = (IIOMetadataNode)imageMetaData.getAsTree(metaFormatName);
            IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");
            tex.setDelay(Integer.parseInt(graphicsControlExtensionNode.getAttribute("delayTime")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tex;
    }

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++) {
            if (rootNode.item(i).getNodeName().equalsIgnoreCase(nodeName)) {
                return((IIOMetadataNode) rootNode.item(i));
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return(node);
    }


}
