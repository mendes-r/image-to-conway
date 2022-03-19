package image.to.conway.service;

import image.to.conway.image.Exporter;
import image.to.conway.image.Importer;
import image.to.conway.image.filter.FilterFactory;
import image.to.conway.image.resample.ResampleFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Autowired
    private final FilterFactory filterFactory;
    @Autowired
    private final ResampleFactory resizeFactory;
    @Autowired
    @Qualifier("${app.exporter}")
    private final Exporter imageExporter;
    @Autowired
    private final Importer imageImporter;

    public Optional<String> uploadImage(String url, int width, int height) {
        try {
            BufferedImage image = imageImporter.importImage(url);
            float ratioWidth = (float) width / image.getWidth();
            float ratioHeight = (float) height / image.getHeight();
            image = resizeFactory.getBilinearResize().resize(image, ratioWidth, ratioHeight);
            image = filterFactory.getBinaryFilter().filter(image);
            String saveUrl = imageExporter.exportImage(image);
            return Optional.of(saveUrl);
        } catch (IllegalArgumentException exception) {
            exception.getStackTrace();
            return Optional.empty();
        }
    }
}
