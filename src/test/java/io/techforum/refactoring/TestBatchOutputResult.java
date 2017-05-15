package io.techforum.refactoring;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class TestBatchOutputResult {

    @Test
    void testOkOutput()
            throws IOException {
        final Stream<String> lines = Files.lines(Paths.get("src/test/resources/ok.txt"));
    }

}
