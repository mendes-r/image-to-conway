package image.to.conway.image;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;

@Component("folder-exporter")
@NoArgsConstructor
public class FolderExporter implements Exporter {

    @Value("${app.save.to.url}")
    String saveToURL;
    @Value("${app.file.type}")
    String fileType;

    @Override
    public String exportImage(BufferedImage image) {
        int count = 0;
        File output;
        String url;
        try {
            do {
                url = saveToURL + Instant.now().getEpochSecond() + "-" + count + "." + fileType;
                output = new File(url);
                count++;
            } while (output.exists());
            ImageIO.write(image, fileType, output);
            return url;
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image was not exported / saved.");
        }
    }
}
