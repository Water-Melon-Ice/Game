package io.github.minetrinity.game.graphics;

import io.github.minetrinity.game.Game;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AnimatedTexture extends Texture {

    private long startedPlaying = -1;
    private long dt = 0;

    private int delay = 0;

    private ArrayList<ImageData> frames = new ArrayList<>();

    public AnimatedTexture() {
        play(true);
    }

    public void play(boolean playing) {
        if (startedPlaying == -1)
            setStartedPlaying(Game.getInstance().startTime);

        if (playing && dt != 0) {
            startedPlaying = System.currentTimeMillis() - dt;
            dt = 0;
        } else if (!playing && dt == 0) {
            dt = System.currentTimeMillis() - startedPlaying;
        }
    }

    public void stop() {
        startedPlaying = -1;
        dt = 0;
    }


    public int getCurrentFrame() {
        if (startedPlaying == -1) return 0;
        int diff;
        int frame;
        if (this.dt == 0) {
            diff = (int) (System.currentTimeMillis() - startedPlaying);
        } else diff = (int) (dt);

        frame = getFrame(Math.abs(diff));

        return frame;
    }

    public int getFrame(int timepassed) {
        if (delay == -1) {
            int f = 0;
            while (timepassed > 0) {
                timepassed -= frames.get(f).delay;
                f++;
                if (f > frames.size() - 1) f = 0;
            }
            return f;
        } else {
            int fpassd = timepassed / delay;
            fpassd = fpassd % frames.size();
            return fpassd;
        }
    }

    public void setCurrentFrame(int frame) {
        if (delay == -1) {
            frame %= frames.size();
            int diff = 0;
            while (frame >= 0) {
                diff += frames.get(frame).delay;
                frame--;
            }
            setStartedPlaying(diff + System.currentTimeMillis());
        } else {
            setStartedPlaying((frame * -delay) + System.currentTimeMillis());
        }
    }

    public void setStartedPlaying(long startedPlaying) {
        if (dt == 0)
            this.startedPlaying = startedPlaying;
        else {
            dt = startedPlaying - System.currentTimeMillis(); //IDK why this works, but it does
            this.startedPlaying = startedPlaying;
        }
    }

    public boolean isPaused(){
        return dt != 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public Image getImage() {
        return getImage(getCurrentFrame());
    }

    public Image getImage(int frame) {
        return frames.get(frame).image;
    }

    @Override
    public void setImage(Image img) {
        setImage(img, getCurrentFrame());
    }

    public void setImage(Image img, int index) {
        frames.get(index).image = img;
    }


    @Override
    public void resizeCut(int width, int height, Color fill) {
        super.resizeCut(width, height, fill);
    }

    @Override
    public void moveCut(int x, int y) {
        super.moveCut(x, y);
    }

    @Override
    public void resizeScale(int width, int height) {
        for(int i = 0; i < frames.size(); i++) {
            setImage(getImage(i).getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT), i);
        }
    }

    @Override
    public Image getImageFillNonOpaque(Color c) {
        return super.getImageFillNonOpaque(c);
    }

    public void remove(int frame) {
        frames.remove(frame);
    }

    public void add(Image i) {
        put(i, frames.size());
    }

    public void put(Image i, int frame) {
        ImageData imd = new ImageData();
        imd.image = i;
        put(imd, frame);
    }

    public void add(ImageData i) {
        put(i, frames.size());
    }

    public void put(ImageData i, int frame) {
        if (i.x != 0 || i.y != 0) {
            int wd = i.x + i.image.getWidth(null);
            int he = i.y + i.image.getHeight(null);
            BufferedImage bufimg = new BufferedImage(wd, he, BufferedImage.TYPE_INT_ARGB);
            bufimg.createGraphics().drawImage(i.image, i.x, i.y, null);
            i.image = bufimg;
            i.x = 0;
            i.y = 0;
        }
        if (frames.size() == 0 || i.delay == delay) delay = i.delay;
        else delay = -1;
        frames.add(frame, i);
        if (width == 0) width = i.image.getWidth(null);
        if (height == 0) height = i.image.getHeight(null);
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

    public void addAll(ImageData... data) {
        for (ImageData i : data) {
            add(i);
        }
    }

    public int getFrameCount() {
        return frames.size();
    }

    public static class ImageData {
        public int x = 0;
        public int y = 0;
        public int delay = 0;
        public Image image;
        public boolean interlaced = false;
    }

}
