package image.to.conway.importer;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Component("url-importer")
@NoArgsConstructor
public class UrlImporter implements Importer {

    /**
     * Imports image from url.
     *
     * @param url url to the image
     * @return image
     */
    @Override
    public BufferedImage importImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }

}
