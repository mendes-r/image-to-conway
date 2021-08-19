package imagetoconway.game;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import imagetoconway.filter.ImageFilter;
import imagetoconway.utils.Constants;

import java.awt.image.BufferedImage;

public class BinaryImage {

    private final BufferedImage image;
    
    /**
     * Sole constructor.
     * 
     * @param url path to image
     */
    public BinaryImage(String url, ImageFilter filter){
        image = filter.convert(url);
    }

    /**
     * Get a boolean matrix from the binary image.
     * black pixel is true, white pixel is false
     * 
     * @return a boolean matrix; a black pixel corresponds to a 'true'
     */
    public boolean[][] toGrid(){
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        boolean[][] grid = new boolean[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Color color = new Color(image.getRGB(j, i));
                if(color.getRed() == Constants.BLACK_RGB){
                    grid[i][j] = true;
                }
            }
        }
        return grid;
    }

    /**
     * Save image.
     * 
     * @param saveToURL path to were we want to save the image
     * @param fileType file tipe
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
