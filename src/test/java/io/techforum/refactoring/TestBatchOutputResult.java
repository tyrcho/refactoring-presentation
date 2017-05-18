package io.techforum.refactoring;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBatchOutputResult {

    @BeforeClass
    public static void setUp() {
        MyBatchProgram.main(numbers_file, "plain_test.txt", "roman_test.txt", "english_test.txt");
    }


    @Test
    public void testPlainConversion() {
        check("plain_test.txt", plain_file);
    }


    @Test // @Ignore
    public void testRomanConversion() {
        check("roman_test.txt", roman_file);
    }


    @Test
    public void testEnglishConversion() {
        check("english_test.txt", english_file);
    }


    private void check(String file, String expected) {
        try {
            assertThat(Files.readAllLines(Paths.get(file))).isEqualTo(Files.readAllLines(Paths.get(expected)));
        } catch(IOException e) {
            fail(e.getMessage());
        }
    }


    private static final String numbers_file = TestBatchOutputResult.class.getResource("/numbers.txt")
                                                                          .getFile();
    private static final String english_file = TestBatchOutputResult.class.getResource("/english_ok.txt")
                                                                          .getFile();
    private static final String plain_file = TestBatchOutputResult.class.getResource("/plain_ok.txt")
                                                                        .getFile();
    private static final String roman_file = TestBatchOutputResult.class.getResource("/roman_ok.txt")
                                                                        .getFile();

}
