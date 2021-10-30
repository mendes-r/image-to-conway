package image.to.conway.service.scaler;

import java.awt.image.BufferedImage;

public class BilinearScaler implements ImageScaler{

    @Override
    public void scale(BufferedImage originalImg, float scaleX, float scaleY) {

        // source https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html

        // Basically if you want to reduce 600x600 to 30x30,
        // you first reduce to 300x300, then 150x150, then 75x75,
        // then 38x38, and only then use bilinear to reduce to 30x30.

        // see https://rosettacode.org/wiki/Bilinear_interpolation#Java

        int newWidth = (int) (originalImg.getWidth() * scaleX);
        int newHeight = (int) (originalImg.getHeight() * scaleY);

        BufferedImage newImg = new BufferedImage(newWidth, newHeight, originalImg.getType());

    }

}
