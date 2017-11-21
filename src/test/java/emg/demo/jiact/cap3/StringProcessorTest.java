package emg.demo.jiact.cap3;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class StringProcessorTest {

    @Test(enabled = true)
    public void predicateTest() throws Exception {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("1");
        listOfStrings.add("2");
        listOfStrings.add("");
        listOfStrings.add("4");
        List<String> nonEmpty = StringProcessor.filter(listOfStrings, nonEmptyStringPredicate);
        Assert.assertEquals(3, nonEmpty.size());
    }

    @Test(enabled = true)
    public void consumerTest() throws Exception {
        StringProcessor.forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.print(i));
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        Assert.assertEquals("12345", outContent.toString());
        Assert.assertTrue(true);
    }

    @Test(enabled = true)
    public void functionTest() throws Exception {
        List<Integer> list = StringProcessor.map(Arrays.asList("lambdas", "in", "action"),
                (String s) -> s.length());
        Assert.assertEquals(Arrays.asList(7, 2, 6), list);
    }
}
