package image.to.conway.service;

import image.to.conway.entities.Grid;
import image.to.conway.image.Exporter;
import image.to.conway.image.Importer;
import image.to.conway.image.filter.FilterFactory;
import image.to.conway.image.filter.ImageFilter;
import image.to.conway.utils.GridUtils;
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
    @Autowired
    Exporter imageExporter;
    @Autowired
    Importer imageImporter;

    @Test
    void saveAnImage() {
        // arrange
        File oldFile = new File(saveToURL);
        if (oldFile.exists()) oldFile.delete();

        ImageFilter filter = filterFactory.getBinaryFilter();
        BufferedImage image = imageImporter.importImage(url);
        image = filter.filter(image);

        // act
        Grid grid = GridUtils.imageToGrid(image);
        //TODO
//        imageExporter.exportImage(grid);

        // assert
        File file = new File(saveToURL);
        assertTrue(file.exists());
    }
}
