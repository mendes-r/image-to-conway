package image.to.conway.utils;

import image.to.conway.constant.RGB;
import image.to.conway.entities.Grid;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class GridUtils {

    /**
     * Sole constructor.
     */
    private GridUtils() {
    }

    /**
     * Prints a given grid to the CLI.
     *
     * @param mask binary grid
     */
    @Deprecated
    public static void printToCLI(boolean[][] mask) {
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
     * Transforms a binary image into a matrix of Cells.
     * Each Cell has an instance variable that tells if the corresponding image pixel is black or white.
     *
     * @param image BufferedImage
     * @return an instance of grid
     */
    public static Grid imageToGrid(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        boolean[][] mask = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(image.getRGB(j, i));
                mask[i][j] = color.getRed() == RGB.BLACK.getCode();
            }
        }
        return new Grid(mask);
    }

    public static BufferedImage gridToImage(Grid grid) {
        // TODO
        return null;
    }

}