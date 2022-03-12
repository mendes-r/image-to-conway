package image.to.conway.image.resizer;

import java.awt.image.BufferedImage;

public interface ImageResize {

    /**
     * Scale image.
     *
     * @param image  Image
     * @param ratioWidth scale magnitude in the x-axis
     * @param ratioHeight scale magnitude in the y-axis
     */
    BufferedImage resize(BufferedImage image, double ratioWidth, double ratioHeight) throws IllegalArgumentException;

}
