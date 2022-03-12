package image.to.conway.image.filter;

import image.to.conway.constant.RGB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
    public BufferedImage filter(BufferedImage image) throws IllegalArgumentException{
        if(Objects.isNull(image)) throw new IllegalArgumentException();

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                // first find grayscale equivalent
                // weighted method, weighs red, green and blue according to their wavelengths
                int red = (int) (color.getRed() * 0.299);
                int green = (int) (color.getGreen() * 0.587);
                int blue = (int) (color.getBlue() * 0.114);
                // define when a pixel is black or white
                if ((red + green + blue) > this.threshold) {
                    Color newColor = new Color(RGB.WHITE.getCode(), RGB.WHITE.getCode(), RGB.WHITE.getCode());
                    newImage.setRGB(i, j, newColor.getRGB());
                } else {
                    Color newColor = new Color(RGB.BLACK.getCode(), RGB.BLACK.getCode(), RGB.BLACK.getCode());
                    newImage.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        return newImage;
    }

}
