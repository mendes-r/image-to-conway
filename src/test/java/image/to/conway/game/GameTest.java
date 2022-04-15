package image.to.conway.game;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("isAliveMaskProvider")
    void isAliveAndHasTwoOrThreeNeighbors_negative(boolean[][] mask) {
        // arrange
        Game game = new Game();

        // act
        boolean result = game.isAliveAndHasTwoOrThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

    static Stream<Arguments> isAliveMaskProvider() {
        return Stream.of(
                // it's not alive
                Arguments.of((Object) new boolean[][]{
                        {false, false, true},
                        {true, false, false},
                        {false, true, false}
                }),
                // too many neighbours
                Arguments.of((Object) new boolean[][]{
                        {true, false, true},
                        {true, true, false},
                        {false, true, false}
                }),
                // one neighbour
                Arguments.of((Object) new boolean[][]{
                        {false, false, false},
                        {true, true, false},
                        {false, false, false}
                })
        );
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

    @ParameterizedTest
    @MethodSource("isDeadMaskProvider")
    void isDeadButHasThreeNeighbors_negative(boolean[][] mask) {
        // arrange
        Game game = new Game();

        // act
        boolean result = game.isDeadButHasThreeNeighbors(mask, 1, 1);

        // assert
        assertFalse(result);
    }

    static Stream<Arguments> isDeadMaskProvider() {
        return Stream.of(
                // its alive
                Arguments.of((Object) new boolean[][]{
                        {false, false, true},
                        {true, true, false},
                        {false, true, false}
                }),
                // just two neighbours
                Arguments.of((Object) new boolean[][]{
                        {false, false, false},
                        {true, false, false},
                        {false, true, false}
                }),
                // four neighbours
                Arguments.of((Object) new boolean[][]{
                        {true, false, true},
                        {true, false, false},
                        {false, true, false}
                })
        );
    }

}
