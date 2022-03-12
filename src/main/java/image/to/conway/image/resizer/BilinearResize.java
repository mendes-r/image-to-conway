package image.to.conway.image.resizer;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Component
public class BilinearResize implements ImageResize {

    /**
     * Scale image using bilinear interpolation.
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     * A - - X - - B
     * - - - + - - -
     * - - - Z - - -
     * - - - + - - -
     * C - - Y - - D
     *
     * @param image original image
     * @param ratioWidth  scale magnitude in the x-axis
     * @param ratioHeight scale magnitude in the y-axis
     */
    @Override
    public BufferedImage resize(BufferedImage image, double ratioWidth, double ratioHeight) throws IllegalArgumentException{
        if(Objects.isNull(image)) throw new IllegalArgumentException();

        int originalWidth = image.getWidth();
        int width = (int) ((originalWidth) * ratioWidth);
        int originalHeight = image.getHeight();
        int height = (int) ((originalHeight) * ratioHeight);

        BufferedImage newImage = new BufferedImage(width, height, image.getType());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                float pY = ((float) (originalHeight - 1) / height) * y;
                float pX = ((float) (originalWidth - 1) / width) * x;

                int pY0 = (int) Math.floor(pY);
                int pY1 = (int) Math.ceil(pY);
                int pX0 = (int) Math.floor(pX);
                int pX1 = (int) Math.ceil(pX);

                Color a = new Color(image.getRGB(pX0, pY0));
                Color b = new Color(image.getRGB(pX0, pY1));
                Color c = new Color(image.getRGB(pX1, pY0));
                Color d = new Color(image.getRGB(pX1, pY1));

                Color z = getColor(a, b, c, d, pX0, pY0, pX1, pY1, pX, pY);

                newImage.setRGB(x, y, z.getRGB());
            }
        }
        return newImage;
    }

    private Color getColor(Color a, Color b, Color c, Color d, int pX0, int pY0, int pX1, int pY1, float x, float y) {
        int red = (int) bilinearInterpolation(a.getRed(), b.getRed(), c.getRed(), d.getRed(), pX0, pY0, pX1, pY1, x, y);
        int green = (int) bilinearInterpolation(a.getGreen(), b.getGreen(), c.getGreen(), d.getGreen(), pX0, pY0, pX1, pY1, x, y);
        int blue = (int) bilinearInterpolation(a.getBlue(), b.getBlue(), c.getBlue(), d.getBlue(), pX0, pY0, pX1, pY1, x, y);
        return new Color(red, green, blue);
    }

    /**
     * Bilinear interpolation
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     *
     * A - - R - - B
     * - - - + - - -
     * - - - Z - - -
     * - - - + - - -
     * C - - G - - D
     *
     * @param a color value
     * @param b color value
     * @param c color value
     * @param d color value
     * @param pX0 starting x
     * @param pY0 starting y
     * @param pX1 ending x
     * @param pY1 ending y
     * @param x point coordinate
     * @param y point coordinate
     * @return z
     */
    private float bilinearInterpolation(int a, int b, int c, int d, int pX0, int pY0, int pX1, int pY1, float x, float y) {
        int r = Math.round(linearInterpolation(a, pY0, b, pY1, y));
        int g = Math.round(linearInterpolation(c, pY0, d, pY1, y));
        return linearInterpolation(r, pX0, g, pX1, x);
    }

    /**
     * Linear interpolation.
     * source: https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html
     *
     * @param a Value of point A
     * @param aStart Coordinates of point A
     * @param b Value of point B
     * @param bEnd Coordinates of point B
     * @param x Coordinates of intermediate point X
     * @return Value of X
     */
    private float linearInterpolation(int a, int aStart, int b, int bEnd, float x) {
        float result;
        if ((bEnd - aStart) <= 0) {
            result = a;
        } else {
            result = (a * ((bEnd - x) / (bEnd - aStart)) + b * ((x - aStart) / (bEnd - aStart)));
        }
        return result;
    }

}
