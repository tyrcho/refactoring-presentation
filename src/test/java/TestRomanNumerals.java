import static org.junit.jupiter.api.Assertions.assertEquals;

import io.techforum.refactoring.MyProgram;
import org.junit.jupiter.api.Test;

/**
 * Created by antoine on 5/12/17.
 */
public class TestRomanNumerals {

    @Test
    public void toRomanUntil500() {
        check(1, "I");
        check(2, "II");
        check(4, "IV");
        check(5, "V");
        check(9, "IX");
        check(8, "VIII");
        check(16, "XVI");
        check(38, "XXXVIII");
        check(42, "XLII");
        check(50, "L");
        check(59, "LIX");
        check(91, "XCI");
        check(100, "C");
        check(256, "CCLVI");
//        check(456, "CDLVI");
    }

    private void check(int number, String expected) {
        String actual = MyProgram.toRomanNumeral(number);
        assertEquals(expected, actual);
    }
}
