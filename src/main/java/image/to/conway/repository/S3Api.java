package image.to.conway.repository;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import image.to.conway.utils.NameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Repository("s3-repository")
@Slf4j
@RequiredArgsConstructor
public class S3Api implements RepositoryApi {

    @Autowired
    AmazonS3 s3;
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${app.file.type}")
    private String fileType;
    @Value("${aws.url.expiration}")
    private long expirationExtension;

    @Override
    public String saveImage(BufferedImage image) {
        initializeBucket();
        try {
            String key = NameGenerator.getAFileName(fileType);
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                ImageIO.write(image, fileType, os);
                byte[] data = os.toByteArray();
                save(data, key);
            }
            return s3.generatePresignedUrl(getPresignedUrlRequest(bucketName, key)).toString();
        } catch (IOException exception) {
            log.warn("Image was not uploaded into the bucket: {}", exception.getMessage());
            throw new IllegalStateException("Image was not exported/saved.");
        }
    }

    @Override
    public String saveImage(byte[] data) {
        initializeBucket();
        String key = NameGenerator.getAFileName("gif");
        try {
            save(data, key);
            return s3.generatePresignedUrl(getPresignedUrlRequest(bucketName, key)).toString();
        } catch (IOException exception) {
            log.warn("Image was not uploaded into the bucket: {}", exception.getMessage());
            throw new IllegalStateException("Image was not exported/saved.");
        }
    }

    private void initializeBucket() {
        if (!s3.doesBucketExistV2(bucketName)) s3.createBucket(bucketName);
    }

    @Override
    public BufferedImage getImage(String key) {
        InputStream stream = s3.getObject(bucketName, key).getObjectContent();
        try {
            return ImageIO.read(stream);
        } catch (IOException exception) {
            log.warn("Image '{}' was not uploaded into the bucket: {}", key, exception.getMessage());
            throw new IllegalArgumentException("The image was not found in S3 bucket " + bucketName + ".");
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

    private void save(byte[] data, String key) throws IOException {
        try (InputStream is = new ByteArrayInputStream(data)) {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(data.length);
            meta.setContentType("image/" + fileType);
            s3.putObject(bucketName, key, is, meta);
        }
    }

}
