package image.to.conway.image.scaler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BilinearScale implements ImageScale {

    /**
     * Scale image using bilinear interpolation.
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     *
     * @param originalImg Image
     * @param scaleX      scale magnitude in the x-axis
     * @param scaleY      scale magnitude in the y-axis
     */
    public BufferedImage scale(BufferedImage originalImg, float scaleX, float scaleY) {

        // Basically if you want to reduce 600x600 to 30x30,
        // you first reduce to 300x300, then 150x150, then 75x75,
        // then 38x38, and only then use bilinear to reduce to 30x30.
        int oldWidth = originalImg.getWidth();
        int newWidth = (int) (oldWidth * scaleX);
        int oldHeight = originalImg.getHeight();
        int newHeight = (int) (oldHeight * scaleY);

        BufferedImage newImg = new BufferedImage(newWidth, newHeight, originalImg.getType());

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {

                int[] zCoord = {x, y};

                // get location of the nearest old pixels
                int aCoordXOld = (int) ((float) x / newWidth * oldWidth);
                int aCoordYOld = (int) ((float) y / newHeight * oldHeight);

                // TODO Bug here: is always [0, 0]
                int aCoordXOldPlusOne;
                if (aCoordXOld + 1 > oldWidth) {
                    aCoordXOldPlusOne = aCoordXOld + 1;
                } else {
                    aCoordXOldPlusOne = aCoordXOld;
                }

                int aCoordYOldPlusOne;
                if (aCoordYOld + 1 > oldHeight) {
                    aCoordYOldPlusOne = aCoordYOld + 1;
                } else {
                    aCoordYOldPlusOne = aCoordYOld;
                }

                Color aValue = new Color(originalImg.getRGB(aCoordXOld, aCoordYOld));
                Color bValue = new Color(originalImg.getRGB(aCoordXOldPlusOne, aCoordYOld));
                Color cValue = new Color(originalImg.getRGB(aCoordXOld, aCoordYOldPlusOne));
                Color dValue = new Color(originalImg.getRGB(aCoordXOldPlusOne, aCoordYOldPlusOne));

                // with the location of old point A, we can calculate the other old points
                // in the new points
                int aCoordX = aCoordXOld * newWidth / oldWidth;
                int aCoordY = aCoordYOld * newHeight / oldHeight;
                int[] aCoord = {aCoordX, aCoordY};

                int bCoordX = (aCoordXOld + 1) * newWidth / oldWidth;
                int bCoordY = aCoordYOld * newHeight / oldHeight;
                int[] bCoord = {bCoordX, bCoordY};

                int cCoordX = aCoordXOld * newWidth / oldWidth;
                int cCoordY = (aCoordYOld + 1) * newHeight / oldHeight;
                int[] cCoord = {cCoordX, cCoordY};

                int dCoordX = (aCoordXOld + 1) * newWidth / oldWidth;
                int dCoordY = (aCoordYOld + 1) * newWidth / oldWidth;
                int[] dCoord = {dCoordX, dCoordY};

                // find Z value through bilinear interpolation
                int zValueRed = (int) bilinearInterpolation(aValue.getRed(), aCoord, bValue.getRed(), bCoord, cValue.getRed(), cCoord, dValue.getRed(), dCoord, zCoord);
                int zValueGreen = (int) bilinearInterpolation(aValue.getGreen(), aCoord, bValue.getGreen(), bCoord, cValue.getGreen(), cCoord, dValue.getGreen(), dCoord, zCoord);
                int zValueBlue = (int) bilinearInterpolation(aValue.getBlue(), aCoord, bValue.getBlue(), bCoord, cValue.getBlue(), cCoord, dValue.getBlue(), dCoord, zCoord);

                if (zValueRed > 255) zValueRed = 255;
                if (zValueRed < 0) zValueRed = 0;
                if (zValueGreen > 255) zValueGreen = 255;
                if (zValueGreen < 0) zValueGreen = 0;
                if (zValueBlue > 255) zValueBlue = 255;
                if (zValueBlue < 0) zValueBlue = 0;

                try {
                    Color zValue = new Color(zValueRed, zValueGreen, zValueBlue);
                    newImg.setRGB(x, y, zValue.getRGB());
                } catch (IllegalArgumentException e) {
                    e.getStackTrace();
                }

            }
        }
        return newImg;
    }

    /**
     * Bilinear interpolation
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     * <p>
     * A - - X - - B
     * - - - + - - -
     * - - - Z - - -
     * - - - + - - -
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
    float bilinearInterpolation(int aValue, int[] aCoord, int bValue, int[] bCoord, int cValue, int[] cCoord, int dValue, int[] dCoord, int[] zCoord) {
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
