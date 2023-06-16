import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Float.POSITIVE_INFINITY;
import static java.lang.Float.NEGATIVE_INFINITY;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest2 {

    @ParameterizedTest
    @MethodSource("TestSum")
    void sum(double a, double b,double answer) {
        Calculator calc = new Calculator();
        assertEquals(answer,calc.sum(a, b));
    }

    public static Object[][] TestSum(){
        return new Object[][]{
                {2,1,3}, {7,8,16}, {0.00000000000021,-0.00000000000002,0.00000000000019},
                {POSITIVE_INFINITY,NEGATIVE_INFINITY,POSITIVE_INFINITY}, {735,-60,675},
                {-0.5,-0.6,-1.1}, {30000, 30000, POSITIVE_INFINITY}, {-30000, 30000, 0},
                {-32000, -32000, NEGATIVE_INFINITY}, {789.5, -789.3, 3.1}
        };
    }

    @ParameterizedTest
    @MethodSource("TestDiff")
    void diff(double a, double b,double answer) {
        Calculator calc = new Calculator();
        assertEquals(answer,calc.diff(a, b));
    }

    public static Object[][] TestDiff(){
        return new Object[][]{
                {2, 2, 0}, {535, 35, 500},
                {74.2, 0.2, 73.8}, {-35, 25, -60},
                {NEGATIVE_INFINITY,POSITIVE_INFINITY,POSITIVE_INFINITY},
                {0.00000005, 0.00000003, 0.00000002}, {0.1, -0.004, 0.111},
                {-30000, 30000, NEGATIVE_INFINITY}, {30000, -30000, 0},
                {31567, 0, 31566}
        };
    }

    @ParameterizedTest
    @MethodSource("TestMult")
    void mult(double a, double b,double answer) {
        Calculator calc = new Calculator();
        assertEquals(answer,calc.mult(a, b));
    }

    public static Object[][] TestMult(){
        return new Object[][]{
                {2, 2, 4}, {-5, 5, -25}, {0, 46, 46},
                {POSITIVE_INFINITY, POSITIVE_INFINITY, POSITIVE_INFINITY},
                {36543, 35, POSITIVE_INFINITY}, {3565, -1, -3565},
                {3.3, -3.3, -9.9}, {30000, -30000, POSITIVE_INFINITY},
                {1, 1, 2}, {35, 3, 105}
        };
    }

    //    @ParameterizedTest
//    @MethodSource("TestDiv")
    @Test
    void div() {
        ArithmeticException calculator = assertThrows(ArithmeticException.class, () -> {
            Calculator calc = new Calculator();
            Assertions.assertEquals("ArithmeticException", calc.div(35, 0));
        });
    }
    @ParameterizedTest
    @MethodSource("TestDiv")
    void div2(double a, double b,double answer) {
        Calculator calc = new Calculator();
        assertEquals(answer,calc.div(a, b));
    }

    public static Object[][] TestDiv(){
        return new Object[][]{
                {2, -1, -2}, {12435,0,POSITIVE_INFINITY},
                {25,2,12.5}, {0.5,0.25, 0.02},
                {12, -6, 2}, {0, 2, 1},
                {NEGATIVE_INFINITY, NEGATIVE_INFINITY, NEGATIVE_INFINITY},
                {53, 4, 13.25}, {60, -3, -20},
                {3, 50, 0.06}
        };
    }
}