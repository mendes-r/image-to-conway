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
     * @param ratioX      scale magnitude in the x-axis
     * @param ratioY      scale magnitude in the y-axis
     */
    public BufferedImage scale(BufferedImage originalImg, float ratioX, float ratioY) {

        int originalWidth = originalImg.getWidth();
        int newWidth = (int) (originalWidth * ratioX);
        int originalHeight = originalImg.getHeight();
        int newHeight = (int) (originalHeight * ratioY);

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, originalImg.getType());

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {

                int[] zCoord = {x, y};

                // get location of the nearest pixels in the original image
                // TODO do here the check if out of bound
                int aCoordXOld = (int) ((float) x / newWidth * (originalWidth - 1));
                int aCoordYOld = (int) ((float) y / newHeight * (originalHeight - 1));

                // TODO when reducing the size... + 1 doesn't work ???
                Color aValue = new Color(originalImg.getRGB(aCoordXOld, aCoordYOld));
                Color bValue = new Color(originalImg.getRGB(aCoordXOld + 1, aCoordYOld));
                Color cValue = new Color(originalImg.getRGB(aCoordXOld, aCoordYOld + 1));
                Color dValue = new Color(originalImg.getRGB(aCoordXOld + 1, aCoordYOld + 1));

                // with the location of the original point A, we can calculate the location in the new image
                // in the new points
                int aCoordX = aCoordXOld * newWidth / (originalWidth - 1);
                int aCoordY = aCoordYOld * newHeight / (originalHeight - 1);
                int[] aCoord = {aCoordX, aCoordY};

                int bCoordX = (aCoordXOld + 1) * newWidth / (originalWidth - 1);
                int bCoordY = aCoordYOld * newHeight / (originalHeight - 1);
                int[] bCoord = {bCoordX, bCoordY};

                int cCoordX = aCoordXOld * newWidth / (originalWidth - 1);
                int cCoordY = (aCoordYOld + 1) * newHeight / (originalHeight - 1);
                int[] cCoord = {cCoordX, cCoordY};

                int dCoordX = (aCoordXOld + 1) * newWidth / (originalWidth - 1);
                int dCoordY = (aCoordYOld + 1) * newWidth / (originalWidth - 1);
                int[] dCoord = {dCoordX, dCoordY};

                // find Z value through bilinear interpolation
                int zValueRed = (int) bilinearInterpolation(aValue.getRed(), aCoord, bValue.getRed(), bCoord, cValue.getRed(), cCoord, dValue.getRed(), dCoord, zCoord);
                int zValueGreen = (int) bilinearInterpolation(aValue.getGreen(), aCoord, bValue.getGreen(), bCoord, cValue.getGreen(), cCoord, dValue.getGreen(), dCoord, zCoord);
                int zValueBlue = (int) bilinearInterpolation(aValue.getBlue(), aCoord, bValue.getBlue(), bCoord, cValue.getBlue(), cCoord, dValue.getBlue(), dCoord, zCoord);

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
