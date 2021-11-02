package image.to.conway.image.scaler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

public class BilinearScalerTest {

    @ParameterizedTest
    @CsvSource({"0,1,200,3,2,100", "0,1,255,3,2,127.5", "0,1,255,4,2,85", "0,0,255,2,1,127.5"})
    void linearInterpolation(int aValue, int aCoord, int bValue, int bCoord, int xCoord, float expected) {
        // arrange
        BilinearScaler scaler = new BilinearScaler();
        float xValue;

        // act
        xValue = scaler.linearInterpolation(aValue, aCoord, bValue, bCoord, xCoord);

        // assert
        Assertions.assertEquals(expected, xValue);
    }

    @Test
    void bilinearInterpolation() {
        // arrange
        BilinearScaler scaler = new BilinearScaler();
        int aValue = 0;
        int[] aCoord = {0, 0};
        int bValue = 200;
        int[] bCoord = {2, 0};
        int cValue = 0;
        int[] cCoord = {0, 2};
        int dValue = 100;
        int[] dCoord = {2, 2};
        int[] zCoord = {1, 1};

        float expected = 75.0f;

        // act
        float zValue = scaler.bilenearInterpolation(aValue, aCoord, bValue, bCoord, cValue, cCoord, dValue, dCoord, zCoord);

        // assert
        Assertions.assertEquals(expected, zValue);
    }

}
