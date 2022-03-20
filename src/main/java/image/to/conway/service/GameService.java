package image.to.conway.service;

import image.to.conway.entities.Grid;
import image.to.conway.game.Game;
import image.to.conway.image.Exporter;
import image.to.conway.image.Importer;
import image.to.conway.utils.GridUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    @Autowired
    private final Game game;
    @Autowired
    private final Importer imageImporter;
    @Autowired
    private final Exporter imageExporter;

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
