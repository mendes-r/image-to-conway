package image.to.conway.service;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag("IntegrationTests")
class ImageServiceIT {

    @Autowired
    ImageService service;
    String firstUrl = "src/test/resources/imagetests/02.jpg";

    @Test
    void uploadImage_success() throws IOException {
        // arrange
        float widthRatio = 2;
        float heightRatio = 2;

        File originalFile = new File(firstUrl);
        BufferedImage originalImage = ImageIO.read(originalFile);
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        // act
        Optional<String> optional = service.uploadImage(firstUrl, widthRatio, heightRatio);

        // assert
        assertTrue(optional.isPresent());
        File resultFile = new File(optional.get());
        assertTrue(resultFile.exists());
        BufferedImage resultImage = ImageIO.read(resultFile);
        int resultWidth = (int)(originalWidth * widthRatio);
        int resultHeight = (int)(originalHeight * heightRatio);
        assertEquals(resultImage.getWidth(), resultWidth);
        assertEquals(resultImage.getHeight(), resultHeight);
    }

    @Test
    void uploadImage_Exception() throws IOException {
        // arrange
        String fakeUrl = "fake/url";

        // act
        Optional<String> optional = service.uploadImage(fakeUrl, 2, 2);

        // assert
        assertTrue(optional.isEmpty());
    }
}
