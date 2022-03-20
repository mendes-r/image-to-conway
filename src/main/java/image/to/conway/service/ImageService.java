package image.to.conway.service;

import image.to.conway.image.Exporter;
import image.to.conway.image.Importer;
import image.to.conway.image.filter.FilterFactory;
import image.to.conway.image.resample.ResampleFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {

    @Autowired
    private final FilterFactory filterFactory;
    @Autowired
    private final ResampleFactory resizeFactory;
    @Autowired
    private final Exporter imageExporter;
    @Autowired
    private final Importer imageImporter;

    public Optional<String> uploadImage(String url, int widthRatio, int heightRatio) {
        try {
            BufferedImage image = imageImporter.importImage(url);
            image = resizeFactory.getBilinearResize().resize(image, widthRatio, heightRatio);
            image = filterFactory.getBinaryFilter().filter(image);
            String saveUrl = imageExporter.exportImage(image);
            return Optional.of(saveUrl);
        } catch (IllegalArgumentException exception) {
            exception.getStackTrace();
            return Optional.empty();
        }
    }
}
