package image.to.conway;

import image.to.conway.image.filter.BinaryFilter;
import image.to.conway.image.filter.ImageFilter;
import image.to.conway.image.scaler.BilinearScale;
import image.to.conway.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String imageName = "012";
        String fileType = "jpg";
        int ration = 200;

        String root = "/Users/ricardomendes/Developer/Projects/";

        String url = root + "image-to-conway/img/" + imageName + "." + fileType;
        String saveToURL = root + "image-to-conway/img/result/result-" + imageName + "." + fileType;
//        String saveToURL2 = root + "image-to-conway/img/result/result-scaled-" + imageName + "." + fileType;
        short threshold = 100;

        BilinearScale scaler = new BilinearScale();
//        ImageFilter filter = new BinaryFilter(threshold);
        BufferedImage image = ImageUtils.url2Image(url);

        // BufferedImage scaled = scaler.proofOfConcept(image, ration);

        image = scaler.scale(image, ration, ration);

        try {
            File output = new File(saveToURL);
//            File output2 = new File(saveToURL2);
            ImageIO.write(image, fileType, output);
//            ImageIO.write(scaled, fileType, output2);
        } catch (IOException exception) {
            exception.getStackTrace();
        }

//        filter.filter(image);

        // more filter and scalers here

//        boolean[][] mask = MaskUtils.imageToMask(image);
//        MaskUtils.printToCLI(mask);

        // Why is the grid so important? Can I not just work with masks?
        // because it has business logic, must be a rectangle
//        Grid grid = new Grid(mask);

//        GridService service = new GridService(grid);

//        service.saveAsImage(saveToURL, fileType);
    }

}
