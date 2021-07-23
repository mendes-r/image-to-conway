package imagetoconway.game;

public class Cell{

    // attribute
    private boolean on = false;

    // constructor
    public Cell(int pixelColor) {
        if (pixelColor == 255) this.on = true;
    }

    // business methods
    public boolean getOn() {
        return this.on;
    }
    
}