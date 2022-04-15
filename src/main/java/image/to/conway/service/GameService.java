package image.to.conway.service;

import image.to.conway.entities.Grid;
import image.to.conway.game.Game;
import image.to.conway.repository.RepositoryApi;
import image.to.conway.image.GifMaker;
import image.to.conway.utils.GridUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameService {

    private final Game game;
    private final RepositoryApi repository;
    private final GifMaker gifMaker;

    @Autowired
    public GameService(Game game, @Qualifier("selectedRepository") RepositoryApi repository, GifMaker gifMaker) {
        this.game = game;
        this.repository = repository;
        this.gifMaker = gifMaker;
    }

    /**
     * Iterates n levels of the game.
     *
     * @return url with the location of the new image
     */
    public Optional<List<String>> getIterations(String key, int iterations) {
        try {
            log.info("Importing and iterating the image.");
            List<BufferedImage> images = iterate(key, iterations);
            log.info("Exporting final images.");
            List<String> urls = exportGrids(images);
            return Optional.of(urls);
        } catch (Exception exception) {
            log.warn("Iteration was not possible: {}", exception.getMessage());
            return Optional.empty();
        }
    }

    public Optional<String> getGif(String url, int iterations) {
        log.info("Creating gif.");
        try {
            byte[] gif = gifMaker.fromImages(iterate(url, iterations));
            String urlGif = repository.saveImage(gif);
            return Optional.of(urlGif);
        } catch (IOException exception) {
            log.warn("Gif was not possible: {}", exception.getMessage());
            return Optional.empty();
        }
    }

    private List<BufferedImage> iterate(String key, int iterations) {
        log.info("Importing and iterating the image.");
        BufferedImage image = repository.getImage(key);
        Grid grid = GridUtils.imageToGrid(image);
        List<Grid> result = this.game.start(grid, iterations);
        return result.stream().map(GridUtils::gridToImage).collect(Collectors.toList());
    }

    private List<String> exportGrids(List<BufferedImage> grids) {
        return grids.stream().map(repository::saveImage).collect(Collectors.toList());
    }

}
