package io.github.minetrinity.game.graphics;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AnimatedTexture extends Texture {

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode) {
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++) {
            if (rootNode.item(i).getNodeName().equalsIgnoreCase("GraphicControlExtension")) {
                return ((IIOMetadataNode) rootNode.item(i));
            }
        }
        IIOMetadataNode node = new IIOMetadataNode("GraphicControlExtension");
        rootNode.appendChild(node);
        return (node);
    }

    private int delay = 50;

    private long startedPlaying = 0;

    private ArrayList<Image> frames = new ArrayList<>();

    public AnimatedTexture() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public Image getImage() {
        return frames.get(getCurrentFrame());
    }

    public void play() {
        if (startedPlaying == 0)
            startedPlaying = System.currentTimeMillis();
    }

    public void stop() {
        startedPlaying = 0;
    }


    public int getCurrentFrame() {
        if(startedPlaying == 0) return 0;
        else{
            int dt = (int) (startedPlaying - System.currentTimeMillis());
            int fpassed = dt / delay;
             return fpassed % getFrameCount();
        }
    }

    @Override
    public void read(InputStream in) {
        ImageReader reader = ImageIO.getImageReadersByFormatName("GIF").next();
        ImageInputStream imgin;
        try {
            imgin = ImageIO.createImageInputStream(in);
            reader.setInput(imgin);
            for (int i = 0; i < reader.getNumImages(true); i++) {
                add(reader.read(i));
            }

            IIOMetadata imageMetaData = null;
            imageMetaData = reader.getImageMetadata(0);
            String metaFormatName = imageMetaData.getNativeMetadataFormatName();
            IIOMetadataNode root = (IIOMetadataNode) imageMetaData.getAsTree(metaFormatName);
            IIOMetadataNode graphicsControlExtensionNode = getNode(root);
            setDelay(Integer.parseInt(graphicsControlExtensionNode.getAttribute("delayTime")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(int frame) {
        frames.remove(frame);
    }

    public void add(Image i) {
        put(i, frames.size());
    }

    public void put(Image i, int frame) {
        frames.add(frame, i);
        if (width == 0) width = i.getWidth(null);
        if (height == 0) height = i.getHeight(null);
    }

    public void addAll(Image... images) {
        for (Image i : images) {
            add(i);
        }
    }

    public void addAll(Texture... textures) {
        for (Texture t : textures) {
            add(t.getImage());
        }
    }

    public int getFrameCount() {
        return frames.size();
    }

    /**
     * delay in milliseconds between frames
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * delay in milliseconds between frames
     */
    public int getDelay() {
        return delay;
    }


}
