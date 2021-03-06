package emg.demo.jiact.cap6;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static emg.demo.jiact.cap6.Dish.menu;
import static org.testng.Assert.assertEquals;

public class CollectorsTest {

    @Test
    public void testGrouping() {
        var menuOptions = menu.stream()
            .collect(Collectors.groupingBy(Dish::isVegetarian));

        System.out.println("menuOptions = " + menuOptions);
        List<Dish> vegetarian = menuOptions.get(true);
        List<Dish> nonVegetarian = menuOptions.get(false);

        assertEquals(4, vegetarian.size());
        assertEquals(5, nonVegetarian.size());
    }

    @Test
    public void testPartitioning() {
        var meatOptions = menu.stream().collect(Collectors.partitioningBy(d -> d.getType().equals(Dish.Type.MEAT)));
        List<Dish> dishes = meatOptions.get(true);
        assertEquals(3, dishes.size());
        System.out.println("meatOptions = " + dishes);
    }

    @Test
    public void testValues() {
        var statistics = menu.stream().mapToInt(Dish::getCalories).summaryStatistics();
        System.out.println("statistics = " + statistics);

        assertEquals(9, statistics.getCount());
    }
}
