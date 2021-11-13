package image.to.conway;

import image.to.conway.game.Game;
import image.to.conway.game.Grid;
import image.to.conway.image.filter.BinaryFilter;
import image.to.conway.image.filter.ImageFilter;
import image.to.conway.image.scaler.BilinearScale;
import image.to.conway.service.GridService;
import image.to.conway.utils.ImageUtils;
import image.to.conway.utils.MaskUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //TODO binary filter doesn't work on mona lisa
        //TODO reduce scale doesn't work
        //TODO who should save the file?

        String imageName = "001";
        String fileType = "jpg";
        float ration = 2.0f;

        String root = "/Users/ricardomendes/Developer/Projects/";

        String url = root + "image-to-conway/img/" + imageName + "." + fileType;
        String saveToURL = root + "image-to-conway/img/result/result1-" + imageName;
        short threshold = 100;

        BilinearScale scaler = new BilinearScale();
        ImageFilter filter = new BinaryFilter(threshold);
        BufferedImage image = ImageUtils.url2Image(url);

        image = scaler.scale(image, ration, ration);
        image = filter.filter(image);


        boolean[][] mask = MaskUtils.imageToMask(image);
        MaskUtils.printToCLI(mask);

        Grid grid = new Grid(mask);

        GridService service = new GridService();

        Game game = new Game(grid);
        game.playNextIteration();
        service.saveAsImage(game.getGrid(), saveToURL, fileType);

    }

}
