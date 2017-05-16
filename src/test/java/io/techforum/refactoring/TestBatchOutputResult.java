package io.techforum.refactoring;


import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestBatchOutputResult {

    @Test
    public void testOkOutput() throws IOException {
        MyBatchProgram.main("numbers.txt target\\plainNumbers.txt target\\romanNumbers.txt target\\englishNumbers.txt".split(" "));

        compare("plainNumbersOk.txt", "plainNumbers.txt");
//        compare("romanNumbersOk.txt", "romanNumbers.txt");
        compare("englishNumbersOk.txt", "englishNumbers.txt");
    }

    private List<String> readLines(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
    }

    private void compare(String expectedFileName, String actualFileName) throws IOException {
        assertEquals("error in " + actualFileName,
                readLines("src/test/resources/" + expectedFileName),
                readLines("target/" + actualFileName));
    }


}
