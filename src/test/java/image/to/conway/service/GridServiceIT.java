package image.to.conway.service;

import image.to.conway.constant.FileType;
import image.to.conway.entities.Grid;
import image.to.conway.image.filter.FilterFactory;
import image.to.conway.image.filter.ImageFilter;
import image.to.conway.importer.ImageExporter;
import image.to.conway.importer.ImageImporter;
import image.to.conway.utils.MaskUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GridServiceIT {

    String root = "/Users/ricardomendes/Developer/Projects/";
    String url = root + "image-to-conway/img/001.jpg";
    String saveToURL = root + "image-to-conway/img/result/002.jpg";

    @Autowired
    FilterFactory filterFactory;

    @Test
    void saveAnImage() {
        // arrange
        File oldFile = new File(saveToURL);
        if (oldFile.exists()) oldFile.delete();

        ImageFilter filter = filterFactory.getBinaryFilter();
        BufferedImage image = ImageImporter.importImage(url);
        image = filter.filter(image);

        // act
        boolean[][] mask = MaskUtils.imageToMask(image);
        Grid grid = new Grid(mask);
        ImageExporter.exportImage(grid, saveToURL, FileType.JPG);

        // assert
        File file = new File(saveToURL);
        assertTrue(file.exists());
    }
}
