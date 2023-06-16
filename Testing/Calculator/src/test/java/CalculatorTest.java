import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void sum1() {
        Calculator calc = new Calculator();
        double ans = 4;
        double o = calc.sum(2,2);
        assertEquals(ans,o);
    }
    @Test
    void sum2() {
        Calculator calc = new Calculator();
        double ans = 100;
        double o = calc.sum(50,50);
        assertEquals(ans,o);
    }

    @Test
    void diff1() {
        Calculator calc = new Calculator();
        double ans = 3;
        double o = calc.diff(6, 3);
        assertEquals(ans, o);
    }


    @Test
    void diff2() {
        Calculator calc = new Calculator();
        double ans = 10;
        double o = calc.diff(20, 10);
        assertEquals(ans, o);
    }

    @Test
    void mult1() {
        Calculator calc = new Calculator();
        double ans = 6;
        double o = calc.mult(2,3);
        assertEquals(ans, o);
    }

    @Test
    void mult2() {
        Calculator calc = new Calculator();
        double ans = -6;
        double o = calc.mult(-2,3);
        assertEquals(ans, o);
    }

    @Test
    void div1() {
        Calculator calc = new Calculator();
        double ans = 5;
        double o = calc.div(25,5);
        assertEquals(ans, o);
    }

    @Test
    void div2() {
        Calculator calc = new Calculator();
        double ans = 10;
        double o = calc.div(100,10);
        assertEquals(ans, o);
    }
}