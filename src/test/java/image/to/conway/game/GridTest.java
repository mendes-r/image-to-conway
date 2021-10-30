package image.to.conway.game;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GridTest {

    @Test(expected = IllegalArgumentException.class)
    public void isNotRectangle() {
        // arrange
        boolean[][] mask = {
                {true, false, false},
                {false, true},
                {true, true, false}
        };

        // act
        new Grid(mask);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNotRectangleNullInput() {
        // arrange
        boolean[][] mask = null;

        // act
        new Grid(mask);
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
                assertTrue(result[i][j] == mask[i][j]);
            }
        }
    }
}
