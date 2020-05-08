package emg.demo.jiact.cap4;

import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasicStreamsTest {

    @Test
    public void basicStreamTest() {
        List<Dish> menu = Dish.menu;

        List<String> highCaloriesVegetrianTop = menu.stream()
            .filter(Dish::isVegetarian)
            .sorted(Comparator.comparing(Dish::getCalories).reversed())
            .map(Dish::getName)
            .limit(3).collect(Collectors.toList());

        assertEquals(3, highCaloriesVegetrianTop.size());
        assertTrue(highCaloriesVegetrianTop.contains("pizza"));
    }
}
