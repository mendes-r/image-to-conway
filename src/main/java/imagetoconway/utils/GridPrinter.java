package imagetoconway.utils;

import imagetoconway.game.Grid;

public class GridPrinter {

    // constructor
    private GridPrinter(){}

    // business methods
    public static void printToCLI(Grid grid) {
        boolean[][] mask = grid.getMask();
        int height = mask.length;
        int width = mask[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mask[i][j]) {
                    System.out.print("x ");
                } else {
                    System.out.print("o ");
                }
                
            }
            System.out.print("\n");
        }
    }


}