package imagetoconway.game;

public class Cell{

    // attribute
    private boolean alive = false;

    // constructor
    public Cell(boolean alive) {
        this.alive = alive;
    }

    // business methods
    public boolean isAlive() {
        return this.alive;
    }
    
}