package image.to.conway.importer;

import image.to.conway.constant.RGB;
import image.to.conway.entities.Grid;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageExporter {

    /**
     * Save mask as a binary image.
     *
     * @param saveToURL where to save the image
     * @param fileType  file type
     */
    public static void exportImage(Grid grid, String saveToURL, String fileType) {
        boolean[][] mask = grid.getMask();
        int height = mask.length;
        int width = mask[0].length;
        BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_BYTE_BINARY);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (mask[i][j]) {
                    Color black = new Color(RGB.BLACK.getCode(), RGB.BLACK.getCode(), RGB.BLACK.getCode());
                    image.setRGB(j, i, black.getRGB());
                } else {
                    Color white = new Color(RGB.WHITE.getCode(), RGB.WHITE.getCode(), RGB.WHITE.getCode());
                    image.setRGB(j, i, white.getRGB());
                }
            }
        }
       exportImage(image, saveToURL, fileType);
    }

    public static void exportImage(BufferedImage image, String saveToURL, String fileType) {
        try {
            File output = new File(saveToURL);
            ImageIO.write(image, fileType, output);
        } catch (IOException exception) {
            exception.getStackTrace();
            throw new IllegalArgumentException("Image was not exported / saved.");
        }
    }
}
