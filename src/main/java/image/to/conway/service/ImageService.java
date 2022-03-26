package image.to.conway.service;

import image.to.conway.image.Exporter;
import image.to.conway.image.Importer;
import image.to.conway.image.filter.FilterFactory;
import image.to.conway.image.resample.ResampleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ImageService {

    private final FilterFactory filterFactory;
    private final ResampleFactory resampleFactory;
    private final Exporter imageExporter;
    private final Importer imageImporter;
    private final Logger logger;

    @Autowired
    public ImageService(FilterFactory filterFactory, ResampleFactory resampleFactory, Importer imageImporter, @Qualifier("selectedExporter") Exporter imageExporter, Logger logger) {
        this.filterFactory = filterFactory;
        this.resampleFactory = resampleFactory;
        this.imageImporter = imageImporter;
        this.imageExporter = imageExporter;
        this.logger = logger;
    }

    public Optional<String> uploadImage(String url, int widthRatio, int heightRatio) {
        try {
            BufferedImage image = imageImporter.importImage(url);
            image = resampleFactory.getBilinearResize().resize(image, widthRatio, heightRatio);
            image = filterFactory.getBinaryFilter().filter(image);
            String saveUrl = imageExporter.exportImage(image);
            return Optional.of(saveUrl);
        } catch (IllegalArgumentException exception) {
            logger.warning("Illegal argument " + exception.getMessage());
            return Optional.empty();
        }
    }
}
