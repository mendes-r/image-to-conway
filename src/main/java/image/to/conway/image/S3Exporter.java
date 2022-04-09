package image.to.conway.image;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import image.to.conway.utils.NameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component("s3-exporter")
@Slf4j
@RequiredArgsConstructor
public class S3Exporter implements Exporter {

    @Autowired
    AmazonS3 s3;
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${app.file.type}")
    private String fileType;
    @Value("${aws.url.expiration}")
    private long expirationExtension;

    @Override
    public String exportImage(BufferedImage image) {

        if (!s3.doesBucketExistV2(bucketName)) {
            s3.createBucket(bucketName);
        }

        try {
            String key = NameGenerator.getAFileName(fileType);
            save(image, key);
            return s3.generatePresignedUrl(getPresignedUrlRequest(bucketName, key)).toString();
        } catch (IOException exception) {
            log.warn("Image was not uploaded into the bucket: {}", exception.getMessage());
            throw new IllegalStateException("Image was not exported/saved.");
        }

    }

    private GeneratePresignedUrlRequest getPresignedUrlRequest(String bucketName, String key) {
        java.util.Date expiration = new java.util.Date();
        long milliSeconds = expiration.getTime();
        milliSeconds += expirationExtension;
        expiration.setTime(milliSeconds);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,
                key);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        generatePresignedUrlRequest.setExpiration(expiration);
        return generatePresignedUrlRequest;
    }

    private void save(BufferedImage image, String key) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(image, fileType, os);
            byte[] buffer = os.toByteArray();
            try (InputStream is = new ByteArrayInputStream(buffer)) {
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentLength(buffer.length);
                meta.setContentType("image/" + fileType);
                s3.putObject(bucketName, key, is, meta);
            }
        }
    }

}
