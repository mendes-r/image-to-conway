package image.to.conway.image.scaler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BilinearScaler implements ImageScaler {

    @Override
    public void scale(BufferedImage originalImg, float scaleX, float scaleY) {

        // source https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html

        // Basically if you want to reduce 600x600 to 30x30,
        // you first reduce to 300x300, then 150x150, then 75x75,
        // then 38x38, and only then use bilinear to reduce to 30x30.

        // see https://rosettacode.org/wiki/Bilinear_interpolation#Java

        int newWidth = (int) (originalImg.getWidth() * scaleX);
        int newHeight = (int) (originalImg.getHeight() * scaleY);

        BufferedImage newImg = new BufferedImage(newWidth, newHeight, originalImg.getType());

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                // get the values of the nearest pixels in the original image

                // find the RGB values on the original image

                // bilinear interpolation calling the linear interpolation function
            }
        }

    }

    /**
     * Bilinear interpolation
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     *
     * A - - X - - B
     *       |
     *       Z
     *       |
     * C - - Y - - D
     *
     * @param aValue Value of point A
     * @param aCoord Coordinates of point A
     * @param bValue Value of point B
     * @param bCoord Coordinates of point B
     * @param cValue Value of point C
     * @param cCoord Coordinates of point C
     * @param dValue Value of point D
     * @param dCoord Coordinates of point D
     * @param zCoord Coordinates of point Z
     * @return Value of point Z
     */
    float bilenearInterpolation(int aValue, int[] aCoord, int bValue, int[] bCoord, int cValue, int[] cCoord, int dValue, int[] dCoord, int[] zCoord) {
        int xValue = Math.round(linearInterpolation(aValue, aCoord[0], bValue, bCoord[0], zCoord[0]));
        int yValue = Math.round(linearInterpolation(cValue, cCoord[0], dValue, dCoord[0], zCoord[0]));
        return linearInterpolation(xValue, aCoord[1], yValue, cCoord[1], zCoord[1]);
    }

    /**
     * Linear interpolation.
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     *
     * @param aValue Value of point A
     * @param aCoord Coordinates of point A
     * @param bValue Value of point B
     * @param bCoord Coordinates of point B
     * @param xCoord Coordinates of intermediate point X
     * @return Value of X
     */
    float linearInterpolation(int aValue, int aCoord, int bValue, int bCoord, int xCoord) {
        return (aValue * (((float) bCoord - xCoord) / (bCoord - aCoord)) + bValue * (((float) xCoord - aCoord) / (bCoord - aCoord)));
    }

}
