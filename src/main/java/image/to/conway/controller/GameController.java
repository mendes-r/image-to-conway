package image.to.conway.controller;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.image.BufferedImage;

@Controller
@AllArgsConstructor
public class GameController implements IGameController{

    @Autowired
    private final ImageService imageService;
    @Autowired
    private final GameService gameService;

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
