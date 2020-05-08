package emg.demo.jiact.cap9;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StrategyMainTest {

    private final StrategyMain strategyMain = new StrategyMain();

    @Test
    public void testValidator() {
        var number = "342342";
        var notanumber = "sdfs";

        Assert.assertTrue(strategyMain.validateNumeric(number));
        Assert.assertFalse(strategyMain.validateNumeric(notanumber));
    }
}