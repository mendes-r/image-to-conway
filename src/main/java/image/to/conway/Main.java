package image.to.conway;

import image.to.conway.service.BinaryFilter;
import image.to.conway.service.ImageFilter;
import image.to.conway.game.*;
import image.to.conway.utils.GridUtils;

public class Main {

    public static void main( String[] args ) {
       
        String url = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/002.jpg";
        String saveToURL = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/result/002.jpg";
        String fileType = "jpg";
        short threshold = 100;

        ImageFilter filter = new BinaryFilter(threshold);

        Grid grid = new Grid(url, filter);

        GridUtils.printToCLI(grid);

        GridUtils.saveAsImage(grid, saveToURL, fileType);
    }

}
