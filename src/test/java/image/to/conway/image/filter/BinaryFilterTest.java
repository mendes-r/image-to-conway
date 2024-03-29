package image.to.conway.image.filter;

import image.to.conway.constant.RGB;
import image.to.conway.importer.Importer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag("UnitTests")
class BinaryFilterTest {

    @Autowired
    @Qualifier("selectedImporter") Importer imageImporter;
    String firstUrl = "src/test/resources/imagetests/01.jpg";

    @Test
    void blackAndWhiteImage() {
        // arrange
        BufferedImage image = imageImporter.importImage(firstUrl);
        short threshold = 100;

        // act
        BufferedImage binaryImage = new BinaryFilter(threshold).filter(image);

        // assert
        assertTrue(isBinary(binaryImage, new IsWhiteOrBlack()));
    }

    @Test
    void whiteImage() {
        // arrange
        BufferedImage image = imageImporter.importImage(firstUrl);
        short threshold = (short) RGB.BLACK.getCode();

        // act
        BufferedImage binaryImage = new BinaryFilter(threshold).filter(image);

        // assert
        assertTrue(isBinary(binaryImage, new IsWhite()));
    }

    @Test
    void blackImage() {
        // arrange
        BufferedImage image = imageImporter.importImage(firstUrl);
        short threshold = (short) RGB.WHITE.getCode();

        // act
        BufferedImage binaryImage = new BinaryFilter(threshold).filter(image);

        // assert
        assertTrue(isBinary(binaryImage, new IsBlack()));
    }

    private boolean isBinary(BufferedImage image, Command command) {
        boolean flag = true;
        int width = image.getWidth() - 1;
        int height = image.getHeight() - 1;
        for (int x = 0; x < width && flag; x++) {
            for (int y = 0; y < height && flag; y++) {
                Color color = new Color(image.getRGB(width, height));
                if (!command.execute(color)) flag = false;
            }
        }
        return flag;
    }

    interface Command {
        boolean execute(Color color);
    }

    static class IsWhiteOrBlack implements Command{
        @Override
        public boolean execute(Color color) {
            boolean flag = color.getRed() == RGB.BLACK.getCode() || color.getRed() == RGB.WHITE.getCode();
            if (!(flag && (color.getGreen() == RGB.BLACK.getCode() || color.getGreen() == RGB.WHITE.getCode()))) flag = false;
            if (!(flag && (color.getBlue() == RGB.BLACK.getCode() || color.getBlue() == RGB.WHITE.getCode()))) flag = false;
            return flag;
        }
    }

    static class IsWhite implements Command{
        @Override
        public boolean execute(Color color) {
            boolean flag = color.getRed() == RGB.WHITE.getCode();
            if (!(flag && color.getGreen() == RGB.WHITE.getCode())) flag = false;
            if (!(flag && color.getBlue() == RGB.WHITE.getCode())) flag = false;
            return flag;
        }
    }

    static class IsBlack implements Command{
        @Override
        public boolean execute(Color color) {
            boolean flag = color.getRed() == RGB.BLACK.getCode();
            if (!(flag && color.getGreen() == RGB.BLACK.getCode())) flag = false;
            if (!(flag && color.getBlue() == RGB.BLACK.getCode())) flag = false;
            return flag;
        }
    }


}
