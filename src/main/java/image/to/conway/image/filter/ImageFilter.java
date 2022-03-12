package image.to.conway.image.filter;

import java.awt.image.BufferedImage;

public interface ImageFilter {

    /**
     * Specific implementation for each subclass of this class.
     *
     * @param image BufferedImage instance
     */
    BufferedImage filter(BufferedImage image) throws IllegalArgumentException;
}
