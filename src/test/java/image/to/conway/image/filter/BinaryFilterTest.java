package image.to.conway.image.filter;

import image.to.conway.constant.RGB;
import image.to.conway.image.Importer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BinaryFilterTest {

    @Autowired
    Importer imageImporter;

    @Test
    void blackAndWhiteImage() {
        // arrange
        BufferedImage image = imageImporter.importImage("src/test/resources/imagetests/01.jpg");
        short threshold = 100;

        // act
        BufferedImage binaryImage = new BinaryFilter(threshold).filter(image);

        // assert
        assertTrue(isBinary(binaryImage, new IsWhiteOrBlack()));
    }

    @Test
    void whiteImage() {
        // arrange
        BufferedImage image = imageImporter.importImage("src/test/resources/imagetests/01.jpg");
        short threshold = (short) RGB.BLACK.getCode();

        // act
        BufferedImage binaryImage = new BinaryFilter(threshold).filter(image);

        // assert
        assertTrue(isBinary(binaryImage, new IsWhite()));
    }

    @Test
    void blackImage() {
        // arrange
        BufferedImage image = imageImporter.importImage("src/test/resources/imagetests/01.jpg");
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
