package imagetoconway.game;

import java.awt.image.BufferedImage;
import java.awt.*;

import imagetoconway.filter.ImageFilter;

public class Grid {

    //attribute
    private final Cell[][] cells;
    private final int height;
    private final int width;

    /**
     * Sole constructor.
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

    
}
