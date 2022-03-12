package image.to.conway.service;

import image.to.conway.image.filter.BinaryFilter;
import image.to.conway.image.resizer.BilinearResize;
import image.to.conway.image.resizer.ImageResize;
import image.to.conway.importer.ImageExporter;
import image.to.conway.importer.ImageImporter;
import image.to.conway.image.filter.ImageFilter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class ImageService {

    // TODO Add to properties

    private static final short THRESHOLD = 100;
    String root = "/";
    String saveToURL = root + "image-to-conway/src/main/resources/000.jpg";
    String fileType = "jpg";

    private final ImageFilter filter = new BinaryFilter(THRESHOLD);
    private final ImageResize resize = new BilinearResize();

    public boolean uploadImage(String url, int width, int height) {
        BufferedImage image = ImageImporter.importImage(url);
        float ratioWidth = (float) width / image.getWidth();
        float ratioHeight = (float) height / image.getHeight();
        try {
            image = filter.filter(resize.resize(image, ratioWidth, ratioHeight));
        } catch (IllegalArgumentException exception) {
            exception.getStackTrace();
        }
        ImageExporter.exportImage(image, saveToURL, fileType);
        return false;
    }
}
