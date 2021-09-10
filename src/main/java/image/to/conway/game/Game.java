package image.to.conway.game;

public class Game {

    //attribute
    private Grid grid;
    private int iteration = 0;

    /**
     * Sole constructor.
     * 
     * @param grid
     */
    public Game(Grid grid){
        if(grid == null) throw new NullPointerException();
        this.grid = grid;
    }
    
    /**
     * Get the number of iterations that already happened.
     * 
     * @return integer
     */
    public int getIteration(){
        return this.iteration;
    }

    /**
     * Play nest iteration.
     * 
     */
    public void playNextIteration(){
        boolean[][] mask = this.grid.getMask();
        int height = mask.length;
        int width = mask[0].length;
        boolean[][] newMask = new boolean[height][width];
        
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if (isAliveAndHasTwoOrThreeNeighbors(mask, i, j) || isDeadButHasThreeNeighbors(mask, i, j)){
                    newMask[i][j] = true;
                }
            }
        }
        this.grid = new Grid(newMask);
    }

    /**
     * Find out how many live neighbors a given position in the matrix has.
     * 
     * @param mask
     * @param row row position of the cell that is being analyzed
     * @param column column position of the cell that is being analysed
     * @return integer representing the number of neighbors
     */
    private int neighbors(boolean[][] mask, int row, int column){
        int numberOfNeighbors = 0;

        for(int i = row - 1; i <= row + 1; i++){
            for(int j = column - 1; j <= column + 1; j++){
                try {
                    if(mask[i][j] && ((i != row) || (j != column))) {
                        numberOfNeighbors ++;
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
     * @param mask
     * @param row row position of the cell that is being analyzed
     * @param column column position of the cell that is being analysed
     * @return boolean
     */
    private boolean isAliveAndHasTwoOrThreeNeighbors(boolean[][] mask, int row, int column){
        return (mask[row][column] && (neighbors(mask, row, column) == 2 || neighbors(mask, row, column) == 3));
    }

    /**
     * The given cell is dead but has three neighbors.
     * 
     * @param mask
     * @param row row position of the cell that is being analyzed
     * @param column column position of the cell that is being analysed
     * @return boolean
     */
    private boolean isDeadButHasThreeNeighbors(boolean[][] mask, int row, int column){
        return (!mask[row][column] && (neighbors(mask, row, column) == 3));
    }
    
}
