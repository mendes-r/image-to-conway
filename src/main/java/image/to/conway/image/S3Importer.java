package image.to.conway.image;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component("s3-importer")
@Slf4j
@RequiredArgsConstructor
public class S3Importer implements Importer {

    @Autowired
    AmazonS3 s3;
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${app.file.type}")
    private String fileType;

    @Override
    public BufferedImage importImage(String url) {
        InputStream stream = s3.getObject(bucketName, url).getObjectContent();
        try {
            return ImageIO.read(stream);
        } catch (IOException exception) {
            log.warn("Image '{}' was not uploaded into the bucket: {}", url, exception.getMessage());
            throw new IllegalArgumentException("The image was not found in S3 bucket " + bucketName + ".");
        }
    }
}
