package image.to.conway.image.resample;

import image.to.conway.importer.Importer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag("UnitTests")
class BilinearResizeTest {

    @Autowired
    @Qualifier("selectedImporter") Importer imageImporter;
    String firstUrl = "src/test/resources/imagetests/01.jpg";

    @Test
    void resizeImageDouble() {
        // arrange
        BufferedImage image = imageImporter.importImage(firstUrl);
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
        BufferedImage image = imageImporter.importImage(firstUrl);
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
