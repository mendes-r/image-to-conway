package imagetoconway;

import imagetoconway.game.*;
import imagetoconway.utils.GridPrinter;

public class Main {

    public static void main( String[] args ) {
        int[][] matrix = 
        {
            {0, 255, 0, 255},
            {255, 0, 255, 0},
            {0, 255, 0, 255},
            {255, 0, 255, 0},
        };

        Grid gameGrid = new Grid(matrix);
        GridPrinter.printToCLI(gameGrid);

        String url = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/002.jpg";
        String saveToURL = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/result/002.jpg";
        String fileType = "jpg";
        short threshold = 100;

        BinaryImage image = new BinaryImage(url, threshold);
        image.save(saveToURL, fileType);
    }

}
