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

        int newWidth = (int) (originalImg.getWidth() * scaleX);
        int newHeight = (int) (originalImg.getHeight() * scaleY);

        BufferedImage newImg = new BufferedImage(newWidth, newHeight, originalImg.getType());

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {

                Color aValue;
                int[] aCoord;
                Color bValue;
                int[] bCoord;
                Color cValue;
                int[] cCoord;
                Color dValue;
                int[] dCoord;
                Color zValue;
                int[] zCoord = {x, y};

                // get the values of the nearest pixels in the original image

                int aCoordX = x / newWidth / originalImg.getWidth();
                int aCoordY = y / newHeight / originalImg.getHeight();

                aCoord = new int[]{aCoordX, aCoordY};
                bCoord = new int[]{aCoordX + 1, aCoordY};
                cCoord = new int[]{aCoordX, aCoordY + 1};
                dCoord = new int[]{aCoordX + 1, aCoordY + 1};

                // find the RGB values on the original image

                aValue = new Color(originalImg.getRGB(aCoord[0], aCoord[1]));
                bValue = new Color(originalImg.getRGB(bCoord[0], bCoord[1]));
                cValue = new Color(originalImg.getRGB(cCoord[0], cCoord[1]));
                dValue = new Color(originalImg.getRGB(dCoord[0], dCoord[1]));

                // bilinear interpolation calling the linear interpolation function

                int zRed = (int) bilinearInterpolation(aValue.getRed(), aCoord, bValue.getRed(), bCoord, cValue.getRed(), cCoord, dValue.getRed(), dCoord, zCoord);
                int zGreen = (int) bilinearInterpolation(aValue.getGreen(), aCoord, bValue.getGreen(), bCoord, cValue.getGreen(), cCoord, dValue.getGreen(), dCoord, zCoord);
                int zBlue = (int) bilinearInterpolation(aValue.getBlue(), aCoord, bValue.getBlue(), bCoord, cValue.getBlue(), cCoord, dValue.getBlue(), dCoord, zCoord);

                zValue = new Color(zRed, zGreen, zBlue);

                newImg.setRGB(x, y, zValue.getRGB());
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
