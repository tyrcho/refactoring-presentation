package io.techforum.refactoring;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRomanNumeralsConversion {

    @Test
    public void testRomanUnitsConversion() {
        check(1, "I");
        check(2, "II");
        check(3, "III");
        check(4, "IV");
        check(5, "V");
        check(6, "VI");
        check(7, "VII");
        check(8, "VIII");
        check(9, "IX");
    }


    @Test
    public void testRomanTensConversion() {
        check(10, "X");
        check(16, "XVI");
        check(38, "XXXVIII");
        check(49, "XLIX");
        check(50, "L");
        check(72, "LXXII");
        check(91, "XCI");
        check(99, "XCIX");
    }


    @Test
    public void testRomanHundredsBelow400Conversion() {
        check(100, "C");
        check(152, "CLII");
        check(256, "CCLVI");
        check(387, "CCCLXXXVII");
    }


    @Test
    public void testRomanHundredsGreaterThan400Conversion() {
        check(400, "CD");
        check(456, "CDLVI");
        check(521, "DXXI");
        check(856, "DCCCLVI");
        check(900, "CM");
        check(999, "CMXCIX");
    }


    private void check(int number, String expected) {
        assertThat(MyBatchProgram.toRomanNumeral(number)).isEqualTo(expected);
    }
}
