package imagetoconway.filter;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

public abstract class ImageFilter {

    public final void convert(String url, String saveToURL, String fileType, short threshold){
        try {
            // source https://memorynotfound.com/convert-image-grayscale-java/
            File input = new File(url);
            System.out.println(input.toPath().toString());
            BufferedImage image = ImageIO.read(input);

            implementFilter(image, threshold);

            File output = new File(saveToURL);
            ImageIO.write(image, fileType, output); 

        } catch (IOException exception) {
            exception.getStackTrace();
        }
    }
    
    protected abstract void implementFilter(BufferedImage image, short threshold);
}
