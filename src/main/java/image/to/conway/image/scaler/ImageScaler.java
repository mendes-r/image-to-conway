package image.to.conway.image.scaler;

import java.awt.image.BufferedImage;

public interface ImageScaler {

    void scale(BufferedImage original, float scaleX, float scaleY);

}
