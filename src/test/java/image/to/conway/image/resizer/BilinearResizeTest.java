package image.to.conway.image.resizer;

import image.to.conway.importer.ImageImporter;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

class BilinearResizeTest {

    @Test
    void resizeImageDouble() {
        // arrange
        BufferedImage image = ImageImporter.importImage("src/test/resources/imagetests/01.jpg");
        int width = image.getWidth();
        int height = image.getHeight();
        int ratio = 2;

        // act
        BufferedImage result = new BilinearResize().resize(image, ratio, ratio);
        int newWidth = result.getWidth();
        int newHeight = result.getHeight();

        // assert
        assertEquals(newWidth, (width * ratio));
        assertEquals(newHeight, (height * ratio));
    }

    @Test
    void resizeImageHalf() {
        // arrange
        BufferedImage image = ImageImporter.importImage("src/test/resources/imagetests/01.jpg");
        int width = image.getWidth();
        int height = image.getHeight();
        double ratio = 0.5;

        // act
        BufferedImage result = new BilinearResize().resize(image, ratio, ratio);
        int newWidth = result.getWidth();
        int newHeight = result.getHeight();

        // assert
        assertEquals(newWidth, Math.floor(width * ratio));
        assertEquals(newHeight, Math.floor(height * ratio));
    }
}
