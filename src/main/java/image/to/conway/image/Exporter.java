package image.to.conway.image;

import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component
public interface Exporter {
    String exportImage(BufferedImage image);
}
