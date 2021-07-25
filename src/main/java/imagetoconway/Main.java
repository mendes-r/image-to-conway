package imagetoconway;

import imagetoconway.filter.BinaryFilter;
import imagetoconway.filter.GrayScaleFilter;
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
        GridPrinter.print(gameGrid);

        String url = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/001.png";
        String saveToURL = "/Users/ricardomendes/Developer/Switch/image-to-conway/img/result/001.png";
        String fileType = "png";
        short threshold = 100;
        
        BinaryFilter biFilter = new BinaryFilter();
        biFilter.convert(url, saveToURL, fileType, threshold);
    }

}
