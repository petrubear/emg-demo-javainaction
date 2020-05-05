package emg.demo.jiact.cap3;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;


public class FileProcessorTest {

    @Test(enabled = true)
    public void processFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data.txt").getFile());

        String oneLine = FileProcessor.processFile(BufferedReader::readLine, file.getAbsolutePath());
        Assert.assertEquals(oneLine, "Line1");

        String twoLines = FileProcessor.processFile((BufferedReader br) -> br.readLine() + br.readLine(), file.getAbsolutePath());
        Assert.assertEquals(twoLines, "Line1Line2");

        Function<String, String> upperCase = String::toUpperCase;
        Assert.assertEquals(upperCase.apply(twoLines), twoLines.toUpperCase());

        Predicate<String> isUpperCase = s -> s.equals(s.toUpperCase());
        Predicate<String> isNotNull = Objects::nonNull;
        Predicate<String> isNotEmpty = s -> !s.isEmpty();

        Assert.assertTrue(isNotNull.or(isNotEmpty).and(isUpperCase).test(upperCase.apply(oneLine)));

    }

}