package image.to.conway.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import image.to.conway.repository.RepositoryApi;
import image.to.conway.importer.Importer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {

    @Autowired
    private ApplicationContext context;
    @Value("${aws.s3.url}")
    private String s3Endpoint;
    @Value("${aws.region}")
    private String region;
    @Value("${aws.access.key}")
    private String accessKey;
    @Value("${aws.secret.key}")
    private String secretKey;


    @Bean
    public RepositoryApi selectedRepository(@Value("${app.repository}") String qualifier) {
        log.info("Repository type: {}", qualifier);
        return (RepositoryApi) context.getBean(qualifier);
    }

    @Bean
    public Importer selectedImporter(@Value("${app.importer}") String qualifier) {
        log.info("Importer type: {}", qualifier);
        return (Importer) context.getBean(qualifier);
    }

    @Bean
    public AmazonS3 s3() {

        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(s3Endpoint, region);
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpoint)
                .enablePathStyleAccess()
                .build();
    }
}
