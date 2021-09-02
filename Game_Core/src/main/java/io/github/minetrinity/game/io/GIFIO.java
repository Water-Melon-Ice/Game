package io.github.minetrinity.game.io;

import io.github.minetrinity.game.graphics.AnimatedTexture;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GIFIO extends ResourceIO<AnimatedTexture> {

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String attribute) {
        for (int i = 0; i < rootNode.getLength(); i++) {
            if (rootNode.item(i).getNodeName().equalsIgnoreCase(attribute)) {
                return ((IIOMetadataNode) rootNode.item(i));
            }
        }
        return null;
    }

    @Override
    public AnimatedTexture read(InputStream in) {
        AnimatedTexture animtex = new AnimatedTexture();
        ImageReader reader = ImageIO.getImageReadersByFormatName("GIF").next();
        ImageInputStream imgin;
        try {
            imgin = ImageIO.createImageInputStream(in);
            reader.setInput(imgin);

            for (int i = 0; i < reader.getNumImages(true); i++) {
                AnimatedTexture.ImageData imd = new AnimatedTexture.ImageData();
                IIOMetadata imageMetaData = null;
                imageMetaData = reader.getImageMetadata(i);
                String metaFormatName = imageMetaData.getNativeMetadataFormatName();
                IIOMetadataNode root = (IIOMetadataNode) imageMetaData.getAsTree(metaFormatName);
                IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");
                int delay = Integer.parseInt(graphicsControlExtensionNode.getAttribute("delayTime")) * 10;
                IIOMetadataNode imgDescriptorNode = getNode(root, "ImageDescriptor");

                imd.image = reader.read(i);
                imd.delay = delay;
                imd.x = Integer.parseInt(imgDescriptorNode.getAttribute("imageLeftPosition"));
                imd.y = Integer.parseInt(imgDescriptorNode.getAttribute("imageTopPosition"));

                animtex.add(imd);
            }
        return animtex;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(OutputStream out, AnimatedTexture obj) {

    }

    @Override
    public boolean isReadable(String format) {
        return format.equalsIgnoreCase("gif");
    }

    @Override
    public boolean isWriteable() {
        return false;
    }
}
