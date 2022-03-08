package image.to.conway.service;

import image.to.conway.image.filter.BinaryFilter;
import image.to.conway.image.resizer.BilinearResize;
import image.to.conway.image.resizer.ImageResize;
import image.to.conway.importer.ImageExporter;
import image.to.conway.importer.ImageImporter;
import image.to.conway.image.filter.ImageFilter;

import java.awt.image.BufferedImage;

public class ImageService {

    // TODO Add to properties
    private final short threshold = 100;
    String root = "/";
    String saveToURL = root + "image-to-conway/src/main/resources/000.jpg";
    String fileType = "jpg";

    private final ImageImporter imageImporter = new ImageImporter();
    private final ImageExporter imageExporter = new ImageExporter();
    private final ImageFilter filter = new BinaryFilter(threshold);
    private final ImageResize resize = new BilinearResize();

    public boolean uploadImage(String url, int width, int height) {
        BufferedImage image = imageImporter.importImage(url);
        float ratioWidth = (float) width / image.getWidth();
        float ratioHeight = (float) height / image.getHeight();
        image = filter.filter(resize.resize(image, ratioWidth, ratioHeight));
        imageExporter.exportImage(image, saveToURL, fileType);
        return false;
    }
}
