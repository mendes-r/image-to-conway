package imagetoconway.game;

public class Grid {

    // attribute
    private final Cell[][] gridBoard;
    private final int height;
    private final int width;


    // constructor
    public Grid(boolean[][] matrix) {
        this.height = matrix.length;
        this.width = matrix[0].length;
        this.gridBoard = new Cell[height][width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.gridBoard[i][j] = new Cell (matrix[i][j]);
            }
        }
    }

    // business methods
    public boolean[][] getMask() {
        //TODO create Iterator to print into the grid
        boolean[][] mask = new boolean[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                mask[i][j] = this.gridBoard[i][j].isAlive();
            }
        }
        return mask;
    }
    
}
