package image.to.conway.game;

import image.to.conway.entities.Grid;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Game {

    public List<Grid> start(Grid grid, int iterations) {
        List<Grid> list = new ArrayList<>();
        Grid temp = grid;
        for (int i = 0; i < iterations; i++) {
            Grid result = iterate(temp);
            temp = result;
            list.add(result);
        }
        return list;
    }

    /**
     * Play next iteration.
     * Game logic with the following rules:
     * 1. Any live cell with two or three live neighbours survives;
     * 2. Any dead cell with three live neighbours becomes a live cell;
     * 3. All other living cells die in the next generation. Similarly, all other dead cells stay dead.
     * source "https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules"
     */
    private Grid iterate(Grid grid) {
        int height = grid.getHeight();
        int width = grid.getWidth();
        boolean[][] mask = grid.getMask();
        boolean[][] newMask = new boolean[height][width];
        // TODO iterator and trying to abdicate the mask
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isAliveAndHasTwoOrThreeNeighbors(mask, i, j) || isDeadButHasThreeNeighbors(mask, i, j)) {
                    newMask[i][j] = true;
                }
            }
        }
        return new Grid(newMask);
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