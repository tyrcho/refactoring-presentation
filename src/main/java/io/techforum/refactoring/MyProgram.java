package io.techforum.refactoring;

import com.ibm.icu.text.RuleBasedNumberFormat;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;

public class MyProgram {

    public void displayNumbers() {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(getClass().getResource("/numbers.txt")
                                                                              .toURI()));
            for(final String line : lines) {
                System.out.println(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void displayRomanNumbers() {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(getClass().getResource("/numbers.txt")
                                                                              .toURI()));
            for(final String line : lines) {
                System.out.println(RomanNumerals.toRomanNumeral(Integer.parseInt(line)));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void displayHumanNumbers() {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(getClass().getResource("/numbers.txt")
                                                                              .toURI()));
            for(final String line : lines) {
                System.out.println(EnglishNumberToWords.convert(Integer.parseInt(line)));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new MyProgram().displayNumbers();
        new MyProgram().displayRomanNumbers();
        new MyProgram().displayHumanNumbers();
    }

}

class RomanNumerals {
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
        String result = "";
        for(int j = 0; j < i; j++) {
            result.concat(s);
        }
        return result;
    }
}

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

class HumanNumbers {
    public static String toHuman(int i) {
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
        return formatter.format(i);
    }
}