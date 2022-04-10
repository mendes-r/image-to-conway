package image.to.conway.game;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTests")
class GameTest {

    @Test
    void isAliveAndHasTwoOrThreeNeighbors_positive() {
        // arrange
        boolean[][] mask = {
                {false, false, true},
                {true, true, false},
                {false, true, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isAliveAndHasTwoOrThreeNeighbors(mask, 1, 1);

        // assert
        assertTrue(result);
    }

    @Test
    void isAliveAndHasTwoOrThreeNeighbors_negative_notAlive() {
        // arrange
        boolean[][] mask = {
                {false, false, true},
                {true, false, false},
                {false, true, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isAliveAndHasTwoOrThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

    @Test
    void isAliveAndHasTwoOrThreeNeighbors_negative_toManyNeighbors() {
        // arrange
        boolean[][] mask = {
                {true, false, true},
                {true, true, false},
                {false, true, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isAliveAndHasTwoOrThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

    @Test
    void isAliveAndHasTwoOrThreeNeighbors_negative_oneNeighbors() {
        // arrange
        boolean[][] mask = {
                {false, false, false},
                {true, true, false},
                {false, false, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isAliveAndHasTwoOrThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

    @Test
    void isDeadButHasThreeNeighbors_positive() {
        // arrange
        boolean[][] mask = {
                {false, false, true},
                {true, false, false},
                {false, true, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isDeadButHasThreeNeighbors(mask, 1, 1);

        // assert
        assertTrue(result);
    }

    @Test
    void isDeadButHasThreeNeighbors_negative_isAlive() {
        // arrange
        boolean[][] mask = {
                {false, false, true},
                {true, true, false},
                {false, true, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isDeadButHasThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

    @Test
    void isDeadButHasThreeNeighbors_negative_justTwoNeighbors() {
        // arrange
        boolean[][] mask = {
                {false, false, false},
                {true, false, false},
                {false, true, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isDeadButHasThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

    @Test
    void isDeadButHasThreeNeighbors_negative_fourNeighbors() {
        // arrange
        boolean[][] mask = {
                {true, false, true},
                {true, false, false},
                {false, true, false}
        };
        Game game = new Game();

        // act
        boolean result = game.isDeadButHasThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

}
