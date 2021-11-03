package image.to.conway;

import image.to.conway.game.Grid;
import image.to.conway.image.filter.BinaryFilter;
import image.to.conway.image.filter.ImageFilter;
import image.to.conway.image.scaler.BilinearScale;
import image.to.conway.image.scaler.ImageScale;
import image.to.conway.service.GridService;
import image.to.conway.utils.ImageUtils;
import image.to.conway.utils.MaskUtils;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        String root = "/Users/ricardomendes/Developer/Projects/";

        String url = root + "image-to-conway/img/001.jpg";
        String saveToURL = root + "image-to-conway/img/result/002.jpg";
        String fileType = "jpg";
        short threshold = 100;

        ImageScale scaler = new BilinearScale();
        ImageFilter filter = new BinaryFilter(threshold);
        BufferedImage image = ImageUtils.url2Image(url);
        image = scaler.scale(image, 2, 2);
        filter.filter(image);

        // more filter and scalers here

        boolean[][] mask = MaskUtils.imageToMask(image);
        MaskUtils.printToCLI(mask);

        // Why is the grid so important? Can I not just work with masks?
        // because it has business logic, must be a rectangle
        Grid grid = new Grid(mask);

        GridService service = new GridService(grid);

        service.saveAsImage(saveToURL, fileType);
    }

}
