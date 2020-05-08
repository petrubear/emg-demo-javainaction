package emg.demo.jiact.cap9;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class StrategyMainTest {

    private final StrategyMain strategyMain = new StrategyMain();

    @Test
    public void testValidator() {
        var number = "342342";
        var notanumber = "sdfs";

        assertTrue(strategyMain.validateNumeric(number));
        assertFalse(strategyMain.validateNumeric(notanumber));
    }
}