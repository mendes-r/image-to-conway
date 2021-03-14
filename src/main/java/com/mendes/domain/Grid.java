package com.mendes.domain;

public class Grid {

    // attribute
    private final Cell[][] cells;
    private final int height;
    private final int width;


    // constructor
    public Grid(int[][] matrix) {
        this.height = matrix.length;
        this.width = matrix[0].length;
        this.cells = new Cell[height][width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.cells[i][j] = new Cell (matrix[i][j]);
            }
        }
    }

    // business methods
    public boolean[][] getMask() {
        boolean[][] mask = new boolean[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                mask[i][j] = this.cells[i][j].getOn();
            }
        }
        return mask;
    }
    
}
