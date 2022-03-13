package image.to.conway.controller;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@AllArgsConstructor
public class GameController implements IGameController{

    @Autowired
    private final ImageService imageService;
    @Autowired
    private final GameService gameService;

    /**
     * Uploads image.
     *
     * @param url image location
     * @param width in pixels
     * @param height in pixels
     * @return true if upload successful
     */
    @PostMapping
    @Override
    public boolean uploadImage(String url, int width, int height) {
        return imageService.uploadImage(url, width, height);
    }

    @GetMapping
    @Override
    public BufferedImage iterate() {
        return null;
    }
}
