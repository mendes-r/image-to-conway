package image.to.conway.entities;

import java.util.Objects;

public class Cell {

    private final boolean alive;

    /**
     * Sole constructor.
     *
     * @param alive boolean
     */
    Cell(boolean alive) {
        this.alive = alive;
    }

    /**
     * Get value of the Cell's instance.
     *
     * @return boolean
     */
    boolean isAlive() {
        return this.alive;
    }

    /**
     * Returns a cell with the opposite value
     *
     * @return cell instance
     */
    Cell toggle() {
        return new Cell(!this.alive);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return alive == cell.alive;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(alive);
    }
}