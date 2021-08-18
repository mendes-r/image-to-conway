package imagetoconway.game;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import imagetoconway.filter.BinaryFilter;
import imagetoconway.filter.ImageFilter;
import java.awt.image.BufferedImage;

public class BinaryImage {

    private final BufferedImage image;
    
    public BinaryImage(String url, short threshold){
        ImageFilter binaryFilter = new BinaryFilter();
        image = binaryFilter.convert(url, threshold);
    }

    /**
     * Get a boolean matrix from the binary image.
     * black pixel is true, white pixel is false
     * 
     * @return
     */
    boolean[][] toGrid(){
        //TODO 
        return null;
    }

    /**
     * Save image
     * 
     */
    public void save(String saveToURL, String fileType) {
        try{
            File output = new File(saveToURL);
            ImageIO.write(this.image, fileType, output);
        } catch(IOException exception) {
            exception.getStackTrace();
        }
    }
    
}
