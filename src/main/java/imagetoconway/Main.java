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
        ConwayGame game = new ConwayGame(gameGrid);
        GridPrinter.print(gameGrid);
    }

}
