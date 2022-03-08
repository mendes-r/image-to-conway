package image.to.conway.entities;

import image.to.conway.entities.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridTest {

    @Test()
    public void isNotRectangle() {
        // arrange
        boolean[][] mask = {
                {true, false, false},
                {false, true},
                {true, true, false}
        };

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> new Grid(mask));
    }

    @Test()
    public void isNotRectangleNullInput() {
        // arrange
        boolean[][] mask = null;

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> new Grid(mask));
    }

    @Test()
    public void isRectangle() {
        // arrange
        boolean[][] mask = {
                {true, false, false},
                {false, true, false},
                {true, true, false}
        };

        // act
        Grid grid = new Grid(mask);
        boolean[][] result = grid.getMask();

        // assert
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                assertEquals(result[i][j], mask[i][j]);
            }
        }
    }
}
