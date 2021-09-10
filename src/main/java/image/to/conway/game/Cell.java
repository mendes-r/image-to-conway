package image.to.conway.game;

public class Cell{

    //attribute
    private boolean alive = false;

    /**
     * Sole constructor.
     * 
     * @param alive boolean
     */
    public Cell(boolean alive) {
        this.alive = alive;
    }

    /**
     * Get value of the Cell's instance.
     * 
     * @return boolean
     */
    public boolean isAlive() {
        return this.alive;
    }
    
}