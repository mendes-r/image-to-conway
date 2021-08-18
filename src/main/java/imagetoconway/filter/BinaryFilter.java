package imagetoconway.filter;

import java.awt.image.BufferedImage;

import imagetoconway.utils.Constants;

import java.awt.*;

public class BinaryFilter extends ImageFilter{

    /**
     * Specific implementation for a binary filter given the ImageFilter template.
     */
    @Override
    protected void implementFilter(BufferedImage image, short threshold) {
        
        if(threshold > 255) throw new IllegalArgumentException("Threshold must be between 0 - 255");

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                // first find grayscale equivalent
                // weighted method, weighs red, green and blue according to their wavelengths
                int red = (int) (color.getRed() * 0.299);
                int green = (int) (color.getGreen() * 0.587);
                int blue = (int) (color.getBlue() * 0.114);
                // define when a pixel is black or white
                if((red + green + blue) > threshold){
                    Color newColor = new Color(Constants.WHITE_RGB, Constants.WHITE_RGB, Constants.WHITE_RGB);
                    image.setRGB(j, i, newColor.getRGB());
                } else {
                    Color newColor = new Color(Constants.BLACK_RGB, Constants.BLACK_RGB, Constants.BLACK_RGB);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
        }
    }
    
}
