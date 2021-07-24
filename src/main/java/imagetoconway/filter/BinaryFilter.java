package imagetoconway.filter;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

public class BinaryFilter implements ImageFilter{

    @Override
    public void convert(String url, String saveToURL, String fileType) {

        final short threshold = 100; 
        final short WHITE = 255;
        final short BLACK = 0;

        try {
            // source https://memorynotfound.com/convert-image-grayscale-java/
            File input = new File(url);
            System.out.println(input.toPath().toString());
            BufferedImage image = ImageIO.read(input);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color c = new Color(image.getRGB(j, i));

                    if(c.getRed() > threshold){
                        Color newColor = new Color(WHITE, WHITE, WHITE);
                        image.setRGB(j, i, newColor.getRGB());
                    } else {
                        Color newColor = new Color(BLACK, BLACK, BLACK);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                }
            }

            File output = new File(saveToURL);
            ImageIO.write(image, fileType, output); 

        } catch (IOException exception) {
            exception.getStackTrace();
        }
        
    }
    
}
