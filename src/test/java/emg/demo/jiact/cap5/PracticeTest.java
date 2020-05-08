package emg.demo.jiact.cap5;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PracticeTest {
    private final Trader raoul = new Trader("Raoul", "Cambridge");
    private final Trader mario = new Trader("Mario", "Milan");
    private final Trader alan = new Trader("Alan", "Cambridge");
    private final Trader brian = new Trader("Brian", "Cambridge");


    private final List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );

    @Test
    public void testTransactions2011() {
        List<Transaction> transactions2011 = transactions.stream()
            .filter(t -> t.getYear() == 2011)
            .sorted(Comparator.comparing(Transaction::getValue))
            .collect(Collectors.toList());
        assertEquals(2, transactions2011.size());
    }

    @Test
    public void testUniqueCities() {
        var cities = transactions.stream()
            .map(t -> t.getTrader().getCity())
            .distinct()
            .collect(Collectors.toList());
        cities.forEach(System.out::println);
        assertEquals(2, cities.size());
    }

    @Test
    public void testCambridgeTraders() {
        var traders = transactions.stream()
            .map(Transaction::getTrader)
            .distinct()
            .filter(t -> t.getCity().equals("Cambridge"))
            .sorted(Comparator.comparing(Trader::getName))
            .collect(Collectors.toList());

        traders.forEach(System.out::println);
        assertEquals(3, traders.size());
    }

    @Test
    public void testAllTraders() {
        var traders = transactions.stream()
            .map(Transaction::getTrader)
            .distinct()
            .map(Trader::getName)
            .reduce((a, b) -> a + ", " + b);

        assertTrue(traders.isPresent());
        System.out.println("traders = " + traders.get());
    }

    @Test
    public void testMilanTraders() {
        var milanTraders = transactions.stream()
            .map(Transaction::getTrader)
            .filter(t -> t.getCity().equals("Milan"))
            .findAny();

        assertTrue(milanTraders.isPresent());
        System.out.println("milanTraders = " + milanTraders.get());
    }

    @Test
    public void testCambridgeTransactions() {
        var cambridgeTransactions = transactions.stream()
            .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue)
            .collect(Collectors.toList());

        assertEquals(4, cambridgeTransactions.size());

        cambridgeTransactions.forEach(System.out::println);
    }

    @Test
    public void testMaxAndMin() {
        var highest = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);

        var smallest = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::min);

        highest.ifPresent(integer -> assertEquals(1000, integer.intValue()));
        smallest.ifPresent(integer -> assertEquals(300, integer.intValue()));

        System.out.println("smallest = " + smallest);
        System.out.println("highest = " + highest);
    }
}
