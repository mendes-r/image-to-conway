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

    float bilinearInterpolation() {
        return 0.0f;
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

    Color colorLinearInterpolation(Color aColor, int aCoord, Color bColor, int bCoord, int xCoord) {
        int aRed = aColor.getRed();
        int bRed = bColor.getRed();
        int aGreen = aColor.getGreen();
        int bGreen = bColor.getGreen();
        int aBlue = aColor.getBlue();
        int bBlue = bColor.getBlue();

        int xRed = Math.round(linearInterpolation(aRed, aCoord, bRed, bCoord, xCoord));
        int xGreen = Math.round(linearInterpolation(aGreen, aCoord, bGreen, bCoord, xCoord));
        int xBlue = Math.round(linearInterpolation(aBlue, aCoord, bBlue, bCoord, xCoord));

        return new Color(xRed, xGreen, xBlue);
    }

}
