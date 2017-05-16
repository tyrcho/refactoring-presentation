package io.techforum.refactoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;

public class MyBatchProgram {

    private String filePath;

    public MyBatchProgram(String path) {
        this.filePath = path;
    }

    public static void main(String[] args) {
        final MyBatchProgram program = new MyBatchProgram(args[0]);
        program.displayNumbers();
        program.displayRomanNumbers();
        program.displayHumanNumbers();
    }


    /**
     * For displaying the numbers in the file...
     */
    public void displayNumbers() {
        try {
            File f = new File(this.filePath);
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while(line != null) {
                System.out.println(line);
                line = bfr.readLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * For displaying the numbers in the file as words...
     */
    public void displayHumanNumbers() {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            for(final String line : lines) {
                System.out.println(EnglishNumberToWords.convert(Integer.parseInt(line)));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * For displaying the numbers in the file as roman numbers...
     */
    public void displayRomanNumbers() {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            for(final String line : lines) {
                System.out.println(toRomanNumeral(Integer.parseInt(line)));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Conversion to a roman numeral...
     */
    public static String toRomanNumeral(int i) {
        String toReturn = baseNumber(i);

        if(toReturn == null) {
            if(i >= 100) {
                return "C" + toRomanNumeral(i - 100);
            }
            if(i >= 90) {
                return "XC" + toRomanNumeral(i - 90);
            }
            if(i >= 50) {
                return "L" + toRomanNumeral(i - 50);
            }
            int nbTens = i / 10;
            if(nbTens > 0 && nbTens < 4) {
                return repeat("X", nbTens) + baseNumber(i % 10);
            } else if(nbTens == 4) {
                return "XL" + baseNumber(i % 10);
            }
        }

        return toReturn;
    }


    public static String baseNumber(int i) {
        if(i == 4) {
            return "IV";
        }
        if(i < 4) {
            return repeat("I", i);
        }
        if(i > 4 && i < 9) {
            return "V" + repeat("I", i - 5);
        }
        if(i == 9) {
            return "IX";
        }
        return null;
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

/**
 * Proud copy pasting of https://stackoverflow.com/questions/3911966/how-to-convert-number-to-words-in-java
 * without even reading the code.
 * Cause why not.
 */
class EnglishNumberToWords {

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };


    private EnglishNumberToWords() {
    }


    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if(number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if(number == 0) {
            return soFar;
        }
        return numNames[number] + " hundred" + soFar;
    }


    public static String convert(long number) {
        // 0 to 999 999 999 999
        if(number == 0) {
            return "zero";
        }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(snumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9, 12));

        String tradBillions;
        switch(billions) {
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertLessThanOneThousand(billions)
                               + " billion ";
                break;
            default:
                tradBillions = convertLessThanOneThousand(billions)
                               + " billion ";
        }
        String result = tradBillions;

        String tradMillions;
        switch(millions) {
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertLessThanOneThousand(millions)
                               + " million ";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions)
                               + " million ";
        }
        result = result + tradMillions;

        String tradHundredThousands;
        switch(hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "one thousand ";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                                       + " thousand ";
        }
        result = result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "")
                     .replaceAll("\\b\\s{2,}\\b", " ");
    }
}