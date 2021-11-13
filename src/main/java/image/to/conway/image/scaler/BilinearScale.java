package image.to.conway.image.scaler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BilinearScale implements ImageScale {

    /**
     * Scale image using bilinear interpolation.
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     * A - - X - - B
     * - - - + - - -
     * - - - Z - - -
     * - - - + - - -
     * C - - Y - - D
     *
     * @param originalImg Image
     * @param scaleX      scale magnitude in the x-axis
     * @param scaleY      scale magnitude in the y-axis
     */
    public BufferedImage scale(BufferedImage originalImg, float scaleX, float scaleY) {

        int originalWidth = originalImg.getWidth();
        int newWidth = (int) (originalWidth * scaleX);
        int originalHeight = originalImg.getHeight();
        int newHeight = (int) (originalHeight * scaleY);

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, originalImg.getType());

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {

                int[] zCoord = {x, y};

                // get location of the nearest pixels in the original image
                int aCoordXOld = (int) ((float) x / newWidth * originalWidth);
                int aCoordYOld = (int) ((float) y / newHeight * originalHeight);

                // be sure that the index is not out of bound
                int aCoordXOldPlusOne = aCoordXOld + 1;
                if (aCoordXOld + 1 <= originalWidth) {
                    aCoordXOldPlusOne = aCoordXOld;
                }

                int aCoordYOldPlusOne = aCoordYOld + 1;
                if (aCoordYOld + 1 <= originalHeight) {
                    aCoordYOldPlusOne = aCoordYOld;
                }

                Color aValue = new Color(originalImg.getRGB(aCoordXOld, aCoordYOld));
                Color bValue = new Color(originalImg.getRGB(aCoordXOldPlusOne, aCoordYOld));
                Color cValue = new Color(originalImg.getRGB(aCoordXOld, aCoordYOldPlusOne));
                Color dValue = new Color(originalImg.getRGB(aCoordXOldPlusOne, aCoordYOldPlusOne));

                // with the location of the original point A, we can calculate the location in the new image
                // in the new points
                int aCoordX = aCoordXOld * newWidth / originalWidth;
                int aCoordY = aCoordYOld * newHeight / originalHeight;
                int[] aCoord = {aCoordX, aCoordY};

                int bCoordX = (aCoordXOld + 1) * newWidth / originalWidth;
                int bCoordY = aCoordYOld * newHeight / originalHeight;
                int[] bCoord = {bCoordX, bCoordY};

                int cCoordX = aCoordXOld * newWidth / originalWidth;
                int cCoordY = (aCoordYOld + 1) * newHeight / originalHeight;
                int[] cCoord = {cCoordX, cCoordY};

                int dCoordX = (aCoordXOld + 1) * newWidth / originalWidth;
                int dCoordY = (aCoordYOld + 1) * newWidth / originalWidth;
                int[] dCoord = {dCoordX, dCoordY};

                // find Z value through bilinear interpolation
                int zValueRed = (int) bilinearInterpolation(aValue.getRed(), aCoord, bValue.getRed(), bCoord, cValue.getRed(), cCoord, dValue.getRed(), dCoord, zCoord);
                int zValueGreen = (int) bilinearInterpolation(aValue.getGreen(), aCoord, bValue.getGreen(), bCoord, cValue.getGreen(), cCoord, dValue.getGreen(), dCoord, zCoord);
                int zValueBlue = (int) bilinearInterpolation(aValue.getBlue(), aCoord, bValue.getBlue(), bCoord, cValue.getBlue(), cCoord, dValue.getBlue(), dCoord, zCoord);

                zValueRed = Math.min(zValueRed, 255);
                zValueRed = Math.max(zValueRed, 0);
                zValueGreen = Math.min(zValueGreen, 255);
                zValueGreen = Math.max(zValueGreen, 0);
                zValueBlue = Math.min(zValueBlue, 255);
                zValueBlue = Math.max(zValueBlue, 0);

                try {
                    Color zValue = new Color(zValueRed, zValueGreen, zValueBlue);
                    newImage.setRGB(x, y, zValue.getRGB());
                } catch (IllegalArgumentException e) {
                    e.getStackTrace();
                }
            }
        }
        return newImage;
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
