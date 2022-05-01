package image.to.conway.repository;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag("UnitTests")
class FileApiTest {

    @Autowired
    @Qualifier("selectedRepository") RepositoryApi api;

    @Test
    void saveImage_BufferedImage_success() throws IOException {
        // arrange
        BufferedImage image = ImageIO.read(new File("src/test/resources/imagetests/01.jpg"));

        // act
        String result = api.saveImage(image);
        File fileResult = new File(result);

        // assert
        assertTrue(fileResult.exists());
    }

    @Test
    void saveImage_BufferedImage_fileDoesntExists() throws IOException {

        // assert
        assertThrows(IllegalArgumentException.class, () -> {
            // arrange
            BufferedImage image = null;

            // act
            String result = api.saveImage(image);
        });
    }

    @Test
    void saveImage_ByteArray_success() throws IOException {
        // arrange
        File file = new File("src/test/resources/imagetests/01.jpg");
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // act
        String result = api.saveImage(fileContent);
        File fileResult = new File(result);

        // assert
        assertTrue(fileResult.exists());
    }

    @Test
    void getImage_success() {
        // arrange
        String url = "src/test/resources/imagetests/01.jpg";

        // act
        BufferedImage image = api.getImage(url);

        // assert
        assertNotNull(image);
    }

    @Test
    void getImage_NoSuchDirectory() {

        assertThrows(IllegalArgumentException.class, () -> {
            // arrange
            String url = "fake/url";

            // act
            BufferedImage image = api.getImage(url);
        });

    }
}
