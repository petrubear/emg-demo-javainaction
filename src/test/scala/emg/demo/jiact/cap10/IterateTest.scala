package emg.demo.jiact.cap10

import org.scalatest.Assertions
import org.testng.annotations.Test

class IterateTest extends Assertions {
  @Test
  def testIterate() {
    Iterate.timesStandard(3, println("Hello Scala Test"))
  }
}
