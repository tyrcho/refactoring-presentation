package io.techforum.refactoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public class NumberConversionBatch {

    private String filePath;


    public NumberConversionBatch(String path) {
        this.filePath = path;
    }


    public static void main(String[] args) {
        final NumberConversionBatch program = new NumberConversionBatch(args[0]);
        program.displayNumbers();
        program.displayRomanNumbers();
        program.displayHumanNumbers();
    }

    public void convertAndDisplay(Function<Integer, String>conversion) {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            for(final String line : lines) {
                System.out.println(conversion.apply(Integer.parseInt(line)));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * For displaying the numbers in the file...
     */
    public void displayNumbers() {
        convertAndDisplay(n -> n.toString());
    }


    /**
     * For displaying the numbers in the file as words...
     */
    public void displayHumanNumbers() {
       convertAndDisplay(n -> EnglishNumberToWords.convert(n));
    }


    /**
     * For displaying the numbers in the file as roman numbers...
     */
    public void displayRomanNumbers() {
        convertAndDisplay(n -> RomanNumeralConverter.toRomanNumeral(n));
    }


}

