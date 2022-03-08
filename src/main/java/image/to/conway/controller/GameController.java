package image.to.conway.controller;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;

import java.awt.image.BufferedImage;

public class GameController implements IGameController{

    private ImageService imageService = new ImageService();
    private GameService gameService = new GameService();

    @Override
    public boolean uploadImage(String url, int width, int height) {
        return imageService.uploadImage(url, width, height);
    }

    @Override
    public BufferedImage iterate() {

        return null;
    }
}
