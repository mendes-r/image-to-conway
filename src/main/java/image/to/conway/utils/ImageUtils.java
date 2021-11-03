package image.to.conway.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    /**
     * Deals with the url 2 BufferedImage logic
     * @param url path to the image
     * @return image
     */
    public static BufferedImage url2Image(String url) {
        try {
            File input = new File(url);
            return ImageIO.read(input);
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }
}
