package image.to.conway.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component("s3-exporter")
@NoArgsConstructor
public class S3Exporter implements Exporter {

    @Autowired
    AmazonS3 s3;
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Override
    public String exportImage(BufferedImage image) {
        if (!s3.doesBucketExistV2(bucketName)) {
            s3.createBucket(bucketName);
        }

        // TODO need to transform image to url and then remove the url after uploaded to the bucket
        String key = "";
        String content = "";

        s3.putObject(
                bucketName,
                key,
                content
        );

        return s3.getUrl(bucketName, key).getPath();
    }
}
