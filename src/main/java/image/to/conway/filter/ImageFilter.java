package image.to.conway.filter;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public abstract class ImageFilter {

    /**
     * Template for an image filter.
     * 
     * @param url image location url
     * @param saveToURL image output in a 'save as' style
     * @param fileType file type, e.g. jpg or png
     * @param threshold used in some filter, e.g. binary filter need to know when a pixel is black or white
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
     * @param image
     */
    protected abstract void implementFilter(BufferedImage image);
}
