package image.to.conway.service;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public abstract class ImageFilter {

    /**
     * Template for an image filter.
     * 
     * @param url image location url
     */
    public final BufferedImage convert(String url){
        try {
            // source https://memorynotfound.com/convert-image-grayscale-java/
            File input = new File(url);
            BufferedImage image = ImageIO.read(input);
            implementFilter(image);
            return image;
            
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }
    
    /**
     * Specific implementation for each subclass of this class.
     * 
     * @param image BufferedImage instance
     */
    protected abstract void implementFilter(BufferedImage image);
}
