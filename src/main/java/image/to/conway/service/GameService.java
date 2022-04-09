package image.to.conway.service;

import image.to.conway.entities.Grid;
import image.to.conway.game.Game;
import image.to.conway.image.Exporter;
import image.to.conway.image.GifMaker;
import image.to.conway.image.Importer;
import image.to.conway.utils.GridUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameService {

    private final Game game;
    private final Importer imageImporter;
    private final Exporter imageExporter;


    @Autowired
    public GameService(Game game, @Qualifier("selectedImporter") Importer imageImporter, @Qualifier("selectedExporter") Exporter imageExporter) {
        this.game = game;
        this.imageImporter = imageImporter;
        this.imageExporter = imageExporter;
    }

    /**
     * Iterates n levels of the game.
     *
     * @return url with the location of the new image
     */
    public Optional<List<String>> getIterations(String url, int iterations) {
        try {
            log.info("Importing and iterating the image.");
            BufferedImage image = imageImporter.importImage(url);
            List<Grid> result = this.game.start(GridUtils.imageToGrid(image), iterations);
            log.info("Exporting final images.");
            // TODO for gifs this shouldn't be necessary: saving to s3 all the iterations
            List<String> urls = exportGrids(result);
            return Optional.of(urls);
        } catch (Exception exception) {
            log.warn("Iteration was not possible: {}", exception.getMessage());
            return Optional.empty();
        }
    }

    private List<String> exportGrids(List<Grid> grids) {
        return grids.stream().map(GridUtils::gridToImage).map(imageExporter::exportImage).collect(Collectors.toList());
    }

    public Optional<String> getGif(String url, int iterations) {
        var optional = getIterations(url, iterations);
        optional.ifPresent(strings -> new GifMaker().fromFiles(strings));
        // TODO return the s3 url from the gif
        return Optional.ofNullable("e");
    }
}
