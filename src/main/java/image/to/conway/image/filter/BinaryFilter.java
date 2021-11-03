package image.to.conway.image.filter;

import image.to.conway.constant.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BinaryFilter implements ImageFilter {

    private final short threshold;

    /**
     * Sole constructor.
     *
     * @param threshold limit that defines what is black and what is white
     */
    public BinaryFilter(short threshold) {
        if (threshold > 255 || threshold < 0) throw new IllegalArgumentException("Threshold must be between 0 - 255");
        this.threshold = threshold;
    }

    /**
     * Specific implementation for a binary filter given the ImageFilter template.
     */
    @Override
    public BufferedImage filter(BufferedImage image) {

        BufferedImage newImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                // first find grayscale equivalent
                // weighted method, weighs red, green and blue according to their wavelengths
                int red = (int) (color.getRed() * 0.299);
                int green = (int) (color.getGreen() * 0.587);
                int blue = (int) (color.getBlue() * 0.114);
                // define when a pixel is black or white
                if ((red + green + blue) > this.threshold) {
                    Color newColor = new Color(Constant.WHITE_RGB, Constant.WHITE_RGB, Constant.WHITE_RGB);
                    newImage.setRGB(j, i, newColor.getRGB());
                } else {
                    Color newColor = new Color(Constant.BLACK_RGB, Constant.BLACK_RGB, Constant.BLACK_RGB);
                    newImage.setRGB(j, i, newColor.getRGB());
                }
            }
        }
        return newImage;
    }

}
