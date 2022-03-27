package image.to.conway.image;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
@NoArgsConstructor
public class FolderImporter implements Importer {

    /**
     * Deals with the url 2 BufferedImage logic
     *
     * @param url path to the image
     * @return image
     */
    @Override
    public BufferedImage importImage(String url) {
        try {
            File input = new File(url);
            return ImageIO.read(input);
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }

}
