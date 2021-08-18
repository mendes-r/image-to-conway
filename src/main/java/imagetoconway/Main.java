package imagetoconway;

import imagetoconway.game.*;
import imagetoconway.utils.GridPrinter;

public class Main {

    public static void main( String[] args ) {
       
        

        String url = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/001.png";
        String saveToURL = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/result/001.png";
        String fileType = "jpg";
        short threshold = 100;

        BinaryImage image = new BinaryImage(url, threshold);
        image.save(saveToURL, fileType);

        Grid grid = new Grid(image.toGrid());

        GridPrinter.printToCLI(grid);
    }

}
