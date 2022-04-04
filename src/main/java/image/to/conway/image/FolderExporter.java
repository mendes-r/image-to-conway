package image.to.conway.image;

import image.to.conway.utils.NameGenerator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component("folder-exporter")
@NoArgsConstructor
public class FolderExporter implements Exporter {

    @Value("${app.save.to.url}")
    String saveToURL;
    @Value("${app.file.type}")
    String fileType;

    @Override
    public String exportImage(BufferedImage image) {
        String url = saveToURL + NameGenerator.getAFileName(fileType);
        File output = new File(url);
        try {
            ImageIO.write(image, fileType, output);
            return url;
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image was not exported/saved.");
        }
    }
}
