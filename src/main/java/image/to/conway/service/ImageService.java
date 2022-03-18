package image.to.conway.service;

import image.to.conway.constant.FileType;
import image.to.conway.image.filter.FilterFactory;
import image.to.conway.image.resample.ResampleFactory;
import image.to.conway.importer.ImageExporter;
import image.to.conway.importer.ImageImporter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Autowired
    private final FilterFactory filterFactory;
    @Autowired
    private final ResampleFactory resizeFactory;

    @Value("${}")
    String saveToURL;

    public Optional<String> uploadImage(String url, int width, int height) {
        try {
            BufferedImage image = ImageImporter.importImage(url);
            float ratioWidth = (float) width / image.getWidth();
            float ratioHeight = (float) height / image.getHeight();
            image = resizeFactory.getBilinearResize().resize(image, ratioWidth, ratioHeight);
            image = filterFactory.getBinaryFilter().filter(image);
            String saveUrl = generateUrl();
            ImageExporter.exportImage(image, saveUrl, FileType.JPG);
            return Optional.of(saveUrl);
        } catch (IllegalArgumentException exception) {
            exception.getStackTrace();
            return Optional.empty();
        }
    }

    private String generateUrl() {
        return saveToURL + Instant.now().getEpochSecond();
    }
}
