package image.to.conway.service;

import image.to.conway.entities.Grid;
import image.to.conway.game.Game;
import image.to.conway.image.Exporter;
import image.to.conway.image.Importer;
import image.to.conway.utils.GridUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final Game game;
    private final Importer imageImporter;
    private final Exporter imageExporter;
    private final Logger logger;

    @Autowired
    public GameService(Game game, Importer imageImporter, @Qualifier("selectedExporter") Exporter imageExporter, Logger logger) {
        this.game = game;
        this.imageImporter = imageImporter;
        this.imageExporter = imageExporter;
        this.logger = logger;
    }

    /**
     * Iterates one level the game.
     *
     * @return url with the location of the new image
     */
    public Optional<List<String>> iterate(String url, int iterations) {
        try {
            BufferedImage image = imageImporter.importImage(url);
            List<Grid> result = this.game.start(GridUtils.imageToGrid(image), iterations);
            List<String> urls = exportGrids(result);
            return Optional.ofNullable(urls);
        } catch (Exception exception) {
            logger.warning("Iteration was not possible: " + exception.getMessage());
            return Optional.empty();
        }
    }

    private List<String> exportGrids(List<Grid> grids) {
        return grids.stream().map(GridUtils::gridToImage).map(imageExporter::exportImage).collect(Collectors.toList());
    }
}
