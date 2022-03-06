package image.to.conway.game;

public class Game {

    private int iterationCount = 0;
    private Grid grid;

    /**
     * Sole constructor.
     *
     * @param grid - binary grid
     */
    public Game(Grid grid) {
        if (grid == null) throw new NullPointerException();
        this.grid = grid;
    }

    /**
     * Get the number of iterations that already happened.
     *
     * @return integer
     */
    public int getIterationCount() {
        return this.iterationCount;
    }

    /**
     * Play next iteration.
     * Game logic with the following rules:
     * 1. Any live cell with two or three live neighbours survives;
     * 2. Any dead cell with three live neighbours becomes a live cell;
     * 3. All other living cells die in the next generation. Similarly, all other dead cells stay dead.
     * source "https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules"
     */
    public void playNextIteration() {
        boolean[][] mask = this.grid.getMask();
        int height = mask.length;
        int width = mask[0].length;
        boolean[][] newMask = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isAliveAndHasTwoOrThreeNeighbors(mask, i, j) || isDeadButHasThreeNeighbors(mask, i, j)) {
                    newMask[i][j] = true;
                }
            }
        }
        this.grid = new Grid(newMask);
        this.iterationCount ++;
    }

    /**
     * Returns a copy of the grid.
     *
     * @return Grid instance
     */
    public Grid getGrid() {
        return new Grid(this.grid.getMask());
    }

    /**
     * Find out how many live neighbors a given position in the matrix has.
     *
     * @param mask   binary grid
     * @param row    row position of the cell that is being analyzed
     * @param column column position of the cell that is being analysed
     * @return integer representing the number of neighbors
     */
    private int neighbors(boolean[][] mask, int row, int column) {
        int numberOfNeighbors = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                try {
                    if (mask[i][j] && ((i != row) || (j != column))) {
                        numberOfNeighbors++;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    exception.getMessage();
                }
            }
        }
        return numberOfNeighbors;
    }

    /**
     * The given cell is alive and has two or three neighbors.
     *
     * @param mask   binary grid
     * @param row    row position of the cell that is being analyzed
     * @param column column position of the cell that is being analysed
     * @return boolean
     */
    private boolean isAliveAndHasTwoOrThreeNeighbors(boolean[][] mask, int row, int column) {
        return (mask[row][column] && (neighbors(mask, row, column) == 2 || neighbors(mask, row, column) == 3));
    }

    /**
     * The given cell is dead but has three neighbors.
     *
     * @param mask   binary grid
     * @param row    row position of the cell that is being analyzed
     * @param column column position of the cell that is being analysed
     * @return boolean
     */
    private boolean isDeadButHasThreeNeighbors(boolean[][] mask, int row, int column) {
        return (!mask[row][column] && (neighbors(mask, row, column) == 3));
    }

}
