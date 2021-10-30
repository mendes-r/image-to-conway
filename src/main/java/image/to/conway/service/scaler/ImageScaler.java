package image.to.conway.service.scaler;

import java.awt.image.BufferedImage;

public interface ImageScaler {

    void scale(BufferedImage original, float scaleX, float scaleY);

}
