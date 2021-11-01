package image.to.conway.image.scaler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

}
