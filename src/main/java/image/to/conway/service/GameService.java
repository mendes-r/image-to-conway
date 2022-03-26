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
import java.util.stream.Collectors;

@Service
public class GameService {

    private final Game game;
    private final Importer imageImporter;
    private final Exporter imageExporter;

    @Autowired
    public GameService(Game game, Importer imageImporter, @Qualifier("selectedExporter") Exporter imageExporter) {
        this.game = game;
        this.imageImporter = imageImporter;
        this.imageExporter = imageExporter;
    }

    /**
     * Iterates one level the game.
     *
     * @return url with the location of the new image
     */
    public Optional<List<String>> iterate(String url, int iterations) {
        // TODO How to validate that the url is form a binary image with the right dimension?
        BufferedImage image = imageImporter.importImage(url);
        List<Grid> result = this.game.start(GridUtils.imageToGrid(image), iterations);
        List<String> urls = exportGrids(result);
        return Optional.ofNullable(urls);
    }

    private List<String> exportGrids(List<Grid> grids) {
        List<String> urls = grids.stream().map(GridUtils::gridToImage).map(imageExporter::exportImage).collect(Collectors.toList());
        return urls;
    }
}
