package io.techforum.refactoring;

import com.ibm.icu.text.RuleBasedNumberFormat;
import io.vavr.control.Try;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

public class NumbersConversionProgram {

    private List<Converter> converters = Arrays.asList(
            new StringConverter(),
            new RomanConverter(),
            new WordConverter()
    );


    private Try<Stream<String>> readFile(String path) {
        return Try.of(() -> Files.lines(Paths.get(this.getClass()
                                                      .getResource(path)
                                                      .toURI())));
    }


    private void display(Stream<String> convertedNumbers) {
        convertedNumbers.forEach(n -> System.out.println(n));
    }


    protected Stream<String> performConversion(Integer number) {
        return converters.stream()
                         .map(c -> c.convert(number));
    }


    public static void main(String[] args) {
        final NumbersConversionProgram program = new NumbersConversionProgram();
        // 1. Retrieve the file content and check it didn't fail
        final Try<Stream<String>> tryReadingNumbers = program.readFile("/numbers.txt");
        if(tryReadingNumbers.isFailure()) {
            System.out.println("Sorry, the file couldn't be read: " + tryReadingNumbers.getCause()
                                                                                       .getMessage());
            System.exit(-1);
        }

        // 2. Retrieve the numbers which have been read
        final Stream<Integer> numbers = tryReadingNumbers.get()
                                                         .map(s -> Integer.parseInt(s));

        // 3. Perform the conversion with all available converters
        final Stream<String> convertedNumbers = numbers.flatMap(n -> program.performConversion(n));

        // 4. Display the converted numbers
        program.display(convertedNumbers);
    }

}

abstract class Converter {
    abstract public String convert(Integer number);
}

class StringConverter
        extends Converter {
    @Override
    public String convert(final Integer number) {
        return number.toString();
    }
}

class RomanConverter
        extends Converter {
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    @Override
    public String convert(Integer number) {
        int l = map.floorKey(number);
        if(number == l) {
            return map.get(number);
        }
        return map.get(l) + convert(number - l);
    }
}

class WordConverter
        extends Converter {
    @Override
    public String convert(final Integer number) {
        return new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT).format(number);
    }
}