package imagetoconway.filter;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

public abstract class ImageFilter {

    /**
     * Image converter.
     * Template for an image filter.
     * 
     * @param url image location url
     * @param saveToURL image output in a 'save as' style
     * @param fileType file type, e.g. jpg or png
     * @param threshold used in some filter, e.g. binary filter need to know when a pixel is black or white
     */
    public final BufferedImage convert(String url, short threshold){
        try {
            // source https://memorynotfound.com/convert-image-grayscale-java/
            File input = new File(url);
            System.out.println(input.toPath().toString());
            BufferedImage image = ImageIO.read(input);

            implementFilter(image, threshold);
            return image;

            
            /* File output = new File(saveToURL);
            ImageIO.write(image, fileType, output);  */

        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }
    
    protected abstract void implementFilter(BufferedImage image, short threshold);
}
