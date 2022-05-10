package image.to.conway.repository;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.util.ReflectionTestUtils;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("UnitTests")
class S3ApiTest {

    String s3Endpoint;
    String region;
    String bucketName = "bucket";
    DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:latest");

    @Rule
    public LocalStackContainer localstack = new LocalStackContainer(localstackImage)
            .withServices(LocalStackContainer.Service.S3);

    RepositoryApi api;

    @BeforeAll
    void setUp() {
        localstack.start();
        s3Endpoint = localstack.getEndpointConfiguration(LocalStackContainer.Service.S3).getServiceEndpoint();
        region = localstack.getEndpointConfiguration(LocalStackContainer.Service.S3).getSigningRegion();
        String accessKey = "xxx";
        String secretKey = "xxx";

        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(s3Endpoint, region);
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpoint)
                .enablePathStyleAccess()
                .build();

        api = new S3Api(s3);
        ReflectionTestUtils.setField(api, "bucketName", this.bucketName);
        ReflectionTestUtils.setField(api, "fileType", "jpg");
        ReflectionTestUtils.setField(api, "expirationExtension", 60000);
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
}
