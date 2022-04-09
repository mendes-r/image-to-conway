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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                mask[j][i] = color.getRed() == RGB.BLACK.getCode();
            }
        }
        return new Grid(mask);
    }

    public static BufferedImage gridToImage(Grid grid) {
        int width = grid.getWidth();
        int height = grid.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // create image iterator with bufferedImage (encapsulate)
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid.cellValue(i, j)) {
                    image.setRGB(i, j, getValue(RGB.WHITE));
                } else {
                    image.setRGB(i, j, getValue(RGB.BLACK));
                }
            }
        }
        return image;
    }

    static int getValue(RGB rgb) {
        return new Color(rgb.getCode(), rgb.getCode(), rgb.getCode()).getRGB();
    }

}