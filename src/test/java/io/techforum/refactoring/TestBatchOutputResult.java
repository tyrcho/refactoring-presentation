package io.techforum.refactoring;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestBatchOutputResult {
    static final String INPUT_TXT = "in/numbers.txt";

    static final String PLAIN_OUTPUT_TXT = "plain_output.txt";
    static final String ROMAN_OUTPUT_TXT = "roman_output.txt";
    static final String ENGLISH_OUTPUT_TXT = "english_output.txt";

    static final List<String> allOutputFiles = Arrays.asList(
            PLAIN_OUTPUT_TXT,
            ROMAN_OUTPUT_TXT,
            ENGLISH_OUTPUT_TXT);

    @BeforeClass
    public static void setUp() throws Exception {
        MyBatchProgram.main(
                getFileLocation(INPUT_TXT),
                PLAIN_OUTPUT_TXT,
                ROMAN_OUTPUT_TXT,
                ENGLISH_OUTPUT_TXT);
    }

    @Test
    public void testPlainConversion() throws Exception {
        check(PLAIN_OUTPUT_TXT, "out/plain_ok.txt");
    }

    @Test
    public void testEnglishConversion() throws Exception {
        check(ENGLISH_OUTPUT_TXT, "out/english_ok.txt");
    }

    @Test
    @Ignore
    public void testRomanConversion() throws Exception {
        check(ROMAN_OUTPUT_TXT, "out/roman_ok.txt");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        for (String outputFile : allOutputFiles) {
            Files.delete(Paths.get(outputFile));
        }
    }

    private void check(String actualOutputFile, String expectedOutputFile) throws Exception {
        assertThat(
                Files.readAllLines(Paths.get(actualOutputFile))).isEqualTo(
                Files.readAllLines(Paths.get(getFileLocation(expectedOutputFile))));
    }

    private static String getFileLocation(String fileName) throws URISyntaxException {
        URL url = TestBatchOutputResult.class.getResource("/" + fileName);
        return Paths.get(url.toURI()).toString();
    }
}
