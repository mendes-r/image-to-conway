package image.to.conway.controller;

import java.awt.image.BufferedImage;

public interface IGameController {

    boolean uploadImage(String url, int width, int height);
    BufferedImage iterate();

}
