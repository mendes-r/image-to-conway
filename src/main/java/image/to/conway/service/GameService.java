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
import java.util.Optional;

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
    public Optional<String> iterate(String url) {
        // TODO How to validate that the url is form a binary image with the right dimension?
        BufferedImage image = imageImporter.importImage(url);
        Grid resultGrid = this.game.iterate(GridUtils.imageToGrid(image));
        BufferedImage resultImage = GridUtils.gridToImage(resultGrid);
        return Optional.ofNullable(imageExporter.exportImage(resultImage));
    }
}
