package image.to.conway.importer;

import java.awt.image.BufferedImage;

public interface Importer {
    BufferedImage importImage(String url);
}
