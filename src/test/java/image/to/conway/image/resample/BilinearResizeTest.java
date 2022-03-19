package image.to.conway.image.resample;

import image.to.conway.image.Importer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BilinearResizeTest {

    @Autowired
    Importer imageImporter;

    @Test
    void resizeImageDouble() {
        // arrange
        BufferedImage image = imageImporter.importImage("src/test/resources/imagetests/01.jpg");
        int width = image.getWidth();
        int height = image.getHeight();
        int ratio = 2;

        // act
        BufferedImage result = new BilinearResample().resize(image, ratio, ratio);
        int newWidth = result.getWidth();
        int newHeight = result.getHeight();

        // assert
        assertEquals(newWidth, (width * ratio));
        assertEquals(newHeight, (height * ratio));
    }

    @Test
    void resizeImageHalf() {
        // arrange
        BufferedImage image = imageImporter.importImage("src/test/resources/imagetests/01.jpg");
        int width = image.getWidth();
        int height = image.getHeight();
        double ratio = 0.5;

        // act
        BufferedImage result = new BilinearResample().resize(image, ratio, ratio);
        int newWidth = result.getWidth();
        int newHeight = result.getHeight();

        // assert
        assertEquals(newWidth, Math.floor(width * ratio));
        assertEquals(newHeight, Math.floor(height * ratio));
    }
}
