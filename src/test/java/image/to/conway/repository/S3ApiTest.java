package image.to.conway.repository;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("UnitTests")
class S3ApiTest {

    String s3Endpoint;
    String region;
    String bucketName = "bucket";
    DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:latest");
    String testKey;

    @Rule
    public LocalStackContainer localstack = new LocalStackContainer(localstackImage)
            .withServices(LocalStackContainer.Service.S3);

    RepositoryApi api;

    @BeforeAll
    void setUp() throws IOException {
        localstack.start();
        AmazonS3 s3 = getS3Client();
        api = new S3Api(s3);

        ReflectionTestUtils.setField(api, "bucketName", this.bucketName);
        ReflectionTestUtils.setField(api, "fileType", "jpg");
        ReflectionTestUtils.setField(api, "expirationExtension", 60000);

        BufferedImage image = ImageIO.read(new File("src/test/resources/imagetests/00.jpg"));
        String presignUrl = api.saveImage(image);
        testKey = getKeyFromUrl(presignUrl);
    }

    private String getKeyFromUrl(String url) {
        return url.split("\\?")[0].split("/")[4];
    }
    private AmazonS3 getS3Client() {
        s3Endpoint = localstack.getEndpointConfiguration(LocalStackContainer.Service.S3).getServiceEndpoint();
        region = localstack.getEndpointConfiguration(LocalStackContainer.Service.S3).getSigningRegion();
        String accessKey = "xxx";
        String secretKey = "xxx";

        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(s3Endpoint, region);
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpoint)
                .enablePathStyleAccess()
                .build();
    }

    @Test
    void saveImage_success() throws IOException {
        // arrange
        BufferedImage image = ImageIO.read(new File("src/test/resources/imagetests/01.jpg"));

        // act
        String presignedUrl = api.saveImage(image);
        String[] result = presignedUrl.split("/", 5);

        // assert
        assertEquals(s3Endpoint, result[0] + "//" + result[2]);
        assertEquals(bucketName, result[3]);
    }

    @Test
    void saveImage_wrongArgument_Null() throws IOException {
        // arrange
        BufferedImage image = null;

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            api.saveImage(image);
        });
    }

    @Test
    void saveImage_wrongArgument_ZeroBytes() {
        // arrange
        byte[] image = {};

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            api.saveImage(image);
        });
    }

    @Test
    void getImage_success() throws IOException {
        // arrange
        BufferedImage original = ImageIO.read(new File("src/test/resources/imagetests/00.jpg"));
        int width = original.getWidth();
        int height = original.getHeight();

        // act
        BufferedImage result = api.getImage(testKey);

        // assert
        assertNotNull(result);
        assertEquals(width, result.getWidth());
        assertEquals(height, result.getHeight());
    }

    @Test
    void getImage_keyNotFound() throws IOException {
        // arrange
        String invalidKey = "invalid";

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            api.getImage(invalidKey);
        });
    }
}
