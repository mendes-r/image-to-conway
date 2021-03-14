package com.mendes.utils;

import com.mendes.domain.Grid;

public class GridPrinter {

    // attributed
    private Grid grid;


    // constructor
    public GridPrinter(Grid grid) {
        this.grid = grid;
    }


    // business methods
    public void print() {
        boolean[][] mask = this.grid.getMask();
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