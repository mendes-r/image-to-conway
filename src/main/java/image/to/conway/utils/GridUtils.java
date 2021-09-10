package image.to.conway.utils;

import image.to.conway.game.Constant;
import image.to.conway.game.Grid;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class GridUtils {

    /**
     * Sole constructor.
     * 
     */
    private GridUtils(){}

    /**
     * Prints a given grid to the CLI.
     * 
     * @param grid
     */
    public static void printToCLI(Grid grid) {
        boolean[][] mask = grid.getMask();
        int height = mask.length;
        int width = mask[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mask[i][j]) {
                    System.out.print("x ");
                } else {
                    System.out.print("o ");
                }
                
            }
            System.out.print("\n");
        }
    }

    /**
     * Save cells as a binary image.
     * 
     */
    public static void saveAsImage(Grid grid, String saveToURL, String fileType){
        boolean[][] mask = grid.getMask();
        int height = mask.length;
        int width = mask[0].length;
        BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_BYTE_BINARY);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if(mask[i][j]){
                    Color black = new Color(Constant.BLACK_RGB, Constant.BLACK_RGB, Constant.BLACK_RGB);
                    image.setRGB(j, i, black.getRGB());
                } else {
                    Color white = new Color(Constant.WHITE_RGB, Constant.WHITE_RGB, Constant.WHITE_RGB);
                    image.setRGB(j, i, white.getRGB());
                }
            }
        }

        try{
            File output = new File(saveToURL);
            ImageIO.write(image, fileType, output);
        } catch(IOException exception) {
            exception.getStackTrace();
        }
    }


}