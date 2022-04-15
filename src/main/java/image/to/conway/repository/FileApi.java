package image.to.conway.repository;

import image.to.conway.utils.NameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Repository("file-repository")
@Slf4j
@RequiredArgsConstructor
public class FileApi implements RepositoryApi{

    @Value("${app.save.to.url}")
    String saveToURL;
    @Value("${app.file.type}")
    String fileType;

    @Override
    public String saveImage(BufferedImage image) {
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

    @Override
    public String saveImage(byte[] data) {
        String url = saveToURL + NameGenerator.getAFileName("gif");
        File file = new File(url);
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(data);
            return url;
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image was not exported/saved.");
        }
    }

    @Override
    public BufferedImage getImage(String dir) {
        try {
            return ImageIO.read(new File(dir));
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image not found. Invalid URL");
        }
    }
}
