package image.to.conway.image.resizer;

import java.awt.image.BufferedImage;

public interface ImageResize {

    /**
     * Scale image.
     *
     * @param image  Image
     * @param scaleX scale magnitude in the x-axis
     * @param scaleY scale magnitude in the y-axis
     */
    BufferedImage scale(BufferedImage image, float scaleX, float scaleY);

}
