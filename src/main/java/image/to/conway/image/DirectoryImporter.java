package image.to.conway.image;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component("folder-importer")
@NoArgsConstructor
public class DirectoryImporter implements Importer {

    /**
     * Deals with the url 2 BufferedImage logic
     *
     * @param url path to the image
     * @return image
     */
    @Override
    public BufferedImage importImage(String url) {
        try {
            // TODO find better option or delete when local env and prod uses S3
            if (StringUtils.startsWithIgnoreCase(url, "http")) {
                return ImageIO.read(new URL(url));
            } else {
                return ImageIO.read(new File(url));
            }
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }

}
