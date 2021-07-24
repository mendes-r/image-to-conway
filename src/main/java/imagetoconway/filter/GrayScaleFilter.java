package imagetoconway.filter;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

public class GrayScaleFilter implements ImageFilter {

    @Override
    public void convert(String url, String saveToURL, String fileType) {
        try {
            // source https://memorynotfound.com/convert-image-grayscale-java/
            File input = new File(url);
            System.out.println(input.toPath().toString());
            BufferedImage image = ImageIO.read(input);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(
                            red + green + blue,
                            red + green + blue,
                            red + green + blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }

            File output = new File(saveToURL);
            ImageIO.write(image, fileType, output); 

        } catch (IOException exception) {
            exception.getStackTrace();
        }
        
    }
    
}
