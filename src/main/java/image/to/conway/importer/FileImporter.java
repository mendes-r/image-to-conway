package image.to.conway.importer;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component("file-importer")
@NoArgsConstructor
public class FileImporter implements Importer {

    /**
     * Imports image from file.
     *
     * @param dir directory to the file
     * @return image
     */
    @Override
    public BufferedImage importImage(String dir) {
        try {
            return ImageIO.read(new File(dir));
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }
}
