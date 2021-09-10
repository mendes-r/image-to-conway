package image.to.conway.game;

import java.awt.image.BufferedImage;
import java.awt.*;

import image.to.conway.filter.ImageFilter;

public class Grid {

    //attribute
    private final Cell[][] cells;
    private final int height;
    private final int width;

    /**
     * Constructor that accepts an image as input.
     * 
     * @param url path to image
     * @param filter type of binary filter
     */
    public Grid(String url, ImageFilter filter){
        if(url == null || filter == null) throw new NullPointerException();
        BufferedImage image = filter.convert(url);
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.cells = imageToCells(image, this.height, this.width);
    }

    /**
     * Constructor that accepts a mask.
     * 
     * @param mask boolean 2D array
     */
    public Grid(boolean[][] mask){
        if(mask == null) throw new NullPointerException();
        if(isARectangle(mask)) throw new IllegalArgumentException("The mask must have a rectangular shape");
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
     * Transforms a binary image into a matrix of Cells.
     * Each Cell has an instance variable that tells if the corresponding image pixel is black or white.
     * 
     * @param image 
     * @param height
     * @param width
     * @return
     */
    private Cell[][] imageToCells(BufferedImage image, int height, int width){
        Cell[][] tempCells = new Cell[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Color color = new Color(image.getRGB(j, i));
                if(color.getRed() == Constant.BLACK_RGB){
                    tempCells[i][j] = new Cell(true);
                } else {
                    tempCells[i][j] = new Cell(false);
                }
            }
        }
        return tempCells;
    }

    /**
     * Returns a mask of the matrix.
     * 
     * @return
     */
    public boolean[][] getMask(){
        boolean[][] mask = new boolean[this.height][this.width];
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(this.cells[i][j].isAlive()){
                    mask[i][j] = true;
                }
            }
        }
        return mask;
    }

    /**
     * Confirms that a given matrix is in the shape of a rectangle.
     * Squares are allowed
     * 
     * @param matrix
     * @return
     */
    private boolean isARectangle(boolean[][] matrix){
        boolean flag = true;
        int height = matrix.length;
        int firstRowLength = matrix[0].length;

        for(int row = 1; row < height && flag; row++){
            if(matrix[row].length != firstRowLength){
                flag = false;
            }
        }

        return flag;
    }
}
