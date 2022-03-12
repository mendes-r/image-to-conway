package image.to.conway.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void cellIsAlive() {
        // arrange
        Cell cell = new Cell(true);

        // act
        boolean result = cell.isAlive();

        // assert
        assertTrue(result);
    }

    @Test
    void cellIsNotAlive() {
        // arrange
        Cell cell = new Cell(false);

        // act
        boolean result = cell.isAlive();

        // assert
        assertFalse(result);
    }

    @Test
    void toggleCellValue() {
        // arrange
        Cell cell = new Cell(false);

        // act
        boolean result = cell.toggle().isAlive();

        // assert
        assertTrue(result);
    }

    @Test
    void toggleReturnsDifferentCell() {
        // arrange
        Cell originalCell = new Cell(true);
        Cell newCell = originalCell.toggle();

        // act
        int originalHash = originalCell.hashCode();
        int newHash = newCell.hashCode();

        // assert
        assertNotEquals(originalHash, newHash);
    }

    @Test
    void equalCells() {
        // arrange
        Cell cell = new Cell(true);
        Cell differentCell = new Cell(true);

        // act and assert
        assertEquals(cell, differentCell);
    }

    @Test
    void notEqualCells() {
        // arrange
        Cell cell = new Cell(true);
        Cell differentCell = new Cell(false);

        // act and assert
        assertNotEquals(cell, differentCell);
    }

    @Test
    void notEqualCellsAfterToggle() {
        // arrange
        Cell cell = new Cell(true);
        Cell differentCell = new Cell(true).toggle();

        // act and assert
        assertNotEquals(cell, differentCell);
    }

    @Test
    void sameCells() {
        // arrange
        Cell cell = new Cell(true);
        Cell sameCell = cell;

        // act and assert
        assertEquals(cell, sameCell);
    }

}
