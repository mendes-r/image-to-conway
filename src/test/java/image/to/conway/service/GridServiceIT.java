package image.to.conway.service;

import image.to.conway.game.Grid;
import image.to.conway.image.filter.BinaryFilter;
import image.to.conway.image.filter.ImageFilter;
import image.to.conway.utils.ImageUtils;
import image.to.conway.utils.MaskUtils;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridServiceIT {

    String root = "/Users/ricardomendes/Developer/Projects/";
    String url = root + "image-to-conway/img/001.jpg";
    String saveToURL = root + "image-to-conway/img/result/002.jpg";
    String fileType = "jpg";

    @Test
    public void saveAnImage() {
        // arrange
        File oldFile = new File(saveToURL);
        if (oldFile.exists()) oldFile.delete();

        short threshold = 100;
        ImageFilter filter = new BinaryFilter(threshold);
        BufferedImage image = ImageUtils.url2Image(url);
        image = filter.filter(image);

        // act
        boolean[][] mask = MaskUtils.imageToMask(image);
        Grid grid = new Grid(mask);
        GridService service = new GridService();
        service.saveAsImage(grid, saveToURL, fileType);

        // assert
        File file = new File(saveToURL);
        assertTrue(file.exists());
    }
}
