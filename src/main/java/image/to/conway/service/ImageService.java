package image.to.conway.service;

import image.to.conway.repository.RepositoryApi;
import image.to.conway.importer.Importer;
import image.to.conway.image.filter.FilterFactory;
import image.to.conway.image.resample.ResampleFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;

@Service
@Slf4j
public class ImageService {

    private final FilterFactory filterFactory;
    private final ResampleFactory resampleFactory;
    private final RepositoryApi repository;
    private final Importer imageImporter;

    @Autowired
    public ImageService(FilterFactory filterFactory, ResampleFactory resampleFactory, @Qualifier("selectedImporter") Importer imageImporter, @Qualifier("selectedRepository") RepositoryApi repository) {
        this.filterFactory = filterFactory;
        this.resampleFactory = resampleFactory;
        this.imageImporter = imageImporter;
        this.repository = repository;
    }

    public Optional<String> uploadImage(String url, float widthRatio, float heightRatio) {
        try {
            log.info("Importing, resizing and filtering image.");
            BufferedImage image = imageImporter.importImage(url);
            image = resampleFactory.getBilinearResize().resize(image, widthRatio, heightRatio);
            image = filterFactory.getBinaryFilter().filter(image);
            log.info("Exporting final image.");
            String saveUrl = repository.saveImage(image);
            return Optional.of(saveUrl);
        } catch (Exception exception) {
            log.warn("Upload was not possible: {}", exception.getMessage());
            return Optional.empty();
        }
    }

}
