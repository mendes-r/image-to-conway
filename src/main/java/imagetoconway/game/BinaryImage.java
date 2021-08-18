package imagetoconway.game;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import imagetoconway.filter.BinaryFilter;
import imagetoconway.filter.ImageFilter;
import imagetoconway.utils.Constants;

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
