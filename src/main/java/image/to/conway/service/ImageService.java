package image.to.conway.service;

import image.to.conway.image.resizer.ImageResize;
import image.to.conway.importer.ImageExporter;
import image.to.conway.importer.ImageImporter;
import image.to.conway.image.filter.ImageFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Autowired
    private final ImageFilter filter;
    @Autowired
    private final ImageResize resize;

    String saveToURL = "image-to-conway/src/main/resources/000.jpg";
    String fileType = "jpg";

    public boolean uploadImage(String url, int width, int height) {
        try {
            BufferedImage image = ImageImporter.importImage(url);
            float ratioWidth = (float) width / image.getWidth();
            float ratioHeight = (float) height / image.getHeight();
            image = filter.filter(resize.resize(image, ratioWidth, ratioHeight));
            ImageExporter.exportImage(image, saveToURL, fileType);
            return true;
        } catch (IllegalArgumentException exception) {
            exception.getStackTrace();
            return false;
        }
    }
}
