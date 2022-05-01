package image.to.conway.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Tag("UnitTests")
class S3ApiTest {

    @InjectMocks
    @Qualifier("s3-repository") RepositoryApi api;

    @Mock
    @Qualifier("s3-repository") AmazonS3 s3;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveImage_success() throws IOException {
        // arrange
        BufferedImage image = ImageIO.read(new File("src/test/resources/imagetests/01.jpg"));
        String url = "url/url";
        // arrange mockito
        when(s3.doesBucketExistV2(any())).thenReturn(false);
        when(s3.createBucket("bucket")).thenReturn(new Bucket());
        when(s3.generatePresignedUrl(any())).thenReturn(new URL(url));

        // act
        String result = api.saveImage(image);

        // assert
        assertEquals(url, result);

    }
}
