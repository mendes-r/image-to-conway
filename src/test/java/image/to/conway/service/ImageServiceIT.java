package image.to.conway.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ImageServiceIT {

    @Autowired
    ImageService imageService;
    String firstUrl = "src/test/resources/imagetests/02.jpg";

    @Test
    void uploadImage() {
        // arrange
        int widthRatio = 2;
        int heightRatio = 2;

        // act
        Optional<String> optional = imageService.uploadImage(firstUrl, widthRatio, heightRatio);

        // assert
        assertTrue(optional.isPresent());
        File file = new File(optional.get());
        assertTrue(file.exists());
    }
}
