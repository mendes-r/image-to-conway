package image.to.conway.entities;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("UnitTests")
class GridTest {

    @Test()
    void isNotRectangle() {
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
    void isNotRectangleNullInput() {
        // arrange
        boolean[][] mask = null;

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> new Grid(mask));
    }

    @Test()
    void isRectangle() {
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
