package image.to.conway.image.scaler;

import java.awt.image.BufferedImage;

public interface ImageScale {

    /**
     * Scale image.
     *
     * @param image  Image
     * @param scaleX scale magnitude in the x-axis
     * @param scaleY scale magnitude in the y-axis
     */
    BufferedImage scale(BufferedImage image, float scaleX, float scaleY);

}
