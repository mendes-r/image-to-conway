package image.to.conway.entities;

import lombok.Getter;

public class Grid {

    private final Cell[][] cells;
    @Getter
    private final int height;
    @Getter
    private final int width;

    /**
     * Sole constructor that accepts a mask.
     *
     * @param mask boolean 2D array
     */
    public Grid(boolean[][] mask) {
        if (mask == null || !isARectangle(mask)) throw new IllegalArgumentException("The mask must have a rectangular shape");
        this.height = mask.length;
        this.width = mask[0].length;
        Cell[][] tempCells = new Cell[height][width];

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                tempCells[row][col] = new Cell(mask[row][col]);
            }
        }

        this.cells = tempCells;
    }

    /**
     * Returns a mask of the matrix.
     *
     * @return a grid of booleans, aka a mask
     */
    @Deprecated
    public boolean[][] getMask() {
        boolean[][] mask = new boolean[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.cells[i][j].isAlive()) {
                    mask[i][j] = true;
                }
            }
        }
        return mask;
    }

    public boolean cellValue(int i, int j) {
        return this.cells[i][j].isAlive();
    }

    /**
     * Confirms that a given matrix is in the shape of a rectangle.
     * Squares are allowed
     *
     * @param matrix a grid of booleans, aka a mask
     * @return true if the grid is a rectangle
     */
    private boolean isARectangle(boolean[][] matrix) {
        boolean flag = true;
        int heigth = matrix.length;
        int firstRowLength = matrix[0].length;

        for (int row = 1; row < heigth && flag; row++) {
            if (matrix[row].length != firstRowLength) {
                flag = false;
            }
        }
        return flag;
    }

}
