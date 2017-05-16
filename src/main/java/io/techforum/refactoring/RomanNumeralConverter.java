package io.techforum.refactoring;

public class RomanNumeralConverter {
    /**
     * Conversion to a roman numeral...
     */
    public static String toRomanNumeral(int i) {
        if(i >= 100) {
            return convertHundreds(i / 100) + toRomanNumeral(i % 100);
        }
        if(i >= 10) {
            return convertTens(i / 10) + toRomanNumeral(i % 10);
        }
        return convertUnits(i);
    }


    public static String convertUnits(int i) {
        return convertGeneric(i, "I", "V", "X");
    }


    public static String convertTens(int i) {
        return convertGeneric(i, "X", "L", "C");
    }


    public static String convertHundreds(int i) {
        return convertGeneric(i, "C", "D", "M");
    }


    public static String convertGeneric(int i, String one, String five, String ten) {
        if(i == 4) {
            return one + five;
        }
        if(i < 4) {
            return repeat(one, i);
        }
        if(i > 4 && i < 9) {
            return five + repeat(one, i - 5);
        }
        if(i == 9) {
            return one + ten;
        }
        return "";
    }


    public static String repeat(String s, int i) {
        //return String.join("", Collections.nCopies(i, s));
        String result = "";
        for(int j = 0; j < i; j++) {
            result += s;
        }
        return result;
    }
}