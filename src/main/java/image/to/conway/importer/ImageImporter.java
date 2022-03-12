package image.to.conway.importer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageImporter {

    /**
     * Deals with the url 2 BufferedImage logic
     * @param url path to the image
     * @return image
     */
    public static BufferedImage importImage(String url) {
        try {
            File input = new File(url);
            return ImageIO.read(input);
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }

}
