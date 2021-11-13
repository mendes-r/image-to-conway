package image.to.conway.service;

import image.to.conway.constant.Constant;
import image.to.conway.game.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridService {


    /**
     * Sole constructor
     */
    public GridService() {
    }

    /**
     * Save mask as a binary image.
     *
     * @param saveToURL where to save the image
     * @param fileType  file type
     */
    public void saveAsImage(Grid grid, String saveToURL, String fileType) {
        boolean[][] mask = grid.getMask();
        int height = mask.length;
        int width = mask[0].length;
        BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_BYTE_BINARY);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (mask[i][j]) {
                    Color black = new Color(Constant.BLACK_RGB, Constant.BLACK_RGB, Constant.BLACK_RGB);
                    image.setRGB(j, i, black.getRGB());
                } else {
                    Color white = new Color(Constant.WHITE_RGB, Constant.WHITE_RGB, Constant.WHITE_RGB);
                    image.setRGB(j, i, white.getRGB());
                }
            }
        }

        try {
            File output = new File(saveToURL);
            ImageIO.write(image, fileType, output);
        } catch (IOException exception) {
            exception.getStackTrace();
        }
    }

}
