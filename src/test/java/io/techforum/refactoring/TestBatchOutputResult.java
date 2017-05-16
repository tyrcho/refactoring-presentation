package io.techforum.refactoring;


import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestBatchOutputResult {

    @Test
    public void testOkOutput()
            throws IOException {
        Stream<String> lines = Files.lines(Paths.get("src/test/resources/ok.txt"));
    }

}
