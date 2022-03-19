package image.to.conway.service;

import image.to.conway.entities.Grid;
import image.to.conway.game.Game;
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

    /**
     * Iterates one level the game.
     *
     * @return url with the location of the new image
     */
    public Optional<String> iterate(String url) {
        BufferedImage image = imageImporter.importImage(url);
        Grid resultGrid = this.game.iterate(GridUtils.imageToGrid(image));
        // Grid to BufferedImage
        // Mask to Grid ...
        return null;
    }
}
