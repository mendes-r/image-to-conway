package image.to.conway.utils;

import image.to.conway.game.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class MaskUtils {

    /**
     * Sole constructor.
     */
    private MaskUtils() {
    }

    /**
     * Prints a given grid to the CLI.
     *
     * @param mask binary grid
     */
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
     * @return a binary grid
     */
    public static boolean[][] imageToMask(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        boolean[][] mask = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(image.getRGB(j, i));
                mask[i][j] = color.getRed() == Constant.BLACK_RGB;
            }
        }
        return mask;
    }

}