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

        String imageName = "020";
        String fileType = "jpg";
        float ration = 0.3f;

        String root = "/Users/ricardomendes/Developer/Projects/";

        String url = root + "image-to-conway/img/" + imageName + "." + fileType;
        String saveToURL = root + "image-to-conway/img/result/result1-" + imageName + "." + fileType;
        short threshold = 100;

        BilinearScale scaler = new BilinearScale();
        ImageFilter filter = new BinaryFilter(threshold);
        BufferedImage image = ImageUtils.url2Image(url);

        image = scaler.scale(image, ration, ration);
        //image = filter.filter(image);

        try {
            File output = new File(saveToURL);
            ImageIO.write(image, fileType, output);
        } catch (IOException exception) {
            exception.getStackTrace();
        }

        // boolean[][] mask = MaskUtils.imageToMask(image);
        // MaskUtils.printToCLI(mask);

        // Grid grid = new Grid(mask);

        // GridService service = new GridService();

        // Game game = new Game(grid);
        // game.playNextIteration();
        // service.saveAsImage(game.getGrid(), saveToURL, fileType);

    }

}
