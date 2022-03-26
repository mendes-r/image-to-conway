package image.to.conway.entities;

public class Grid {

    private final boolean[][] cells;

    /**
     * Sole constructor that accepts a mask.
     *
     * @param mask boolean 2D array
     */
    public Grid(boolean[][] mask) {
        if (mask == null || !isARectangle(mask)) throw new IllegalArgumentException("The mask must have a rectangular shape");
        this.cells = mask;
    }

    public boolean[][] getMask() {
        return this.cells;
    }

    public int getHeight() {
        return cells.length;
    }

    public int getWidth() {
        return cells[0].length;
    }

    public boolean cellValue(int i, int j) {
        return this.cells[j][i];
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
        int height = matrix.length;
        int firstRowLength = matrix[0].length;

        for (int row = 1; row < height && flag; row++) {
            if (matrix[row].length != firstRowLength) {
                flag = false;
            }
        }
        return flag;
    }

}
