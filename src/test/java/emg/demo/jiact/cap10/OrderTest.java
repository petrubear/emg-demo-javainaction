package emg.demo.jiact.cap10;

import emg.demo.jiact.cap10.model.Order;
import emg.demo.jiact.cap10.model.Product;
import org.testng.annotations.Test;

import static emg.demo.jiact.cap10.MethodChainingOrderBuilder.forCustomer;
import static org.testng.AssertJUnit.assertEquals;

public class OrderTest {

    @Test
    public void testGetValue() {
        var order = new Order();
        order.setCustomer("Edison");

        var jacket = new Product();
        jacket.setType(Product.Type.GEAR);
        jacket.setName("alpinestars");
        jacket.setPrice(400);
        jacket.setQuantity(2);
        order.addProduct(jacket);

        System.out.println("order = " + order);
        assertEquals(order.getCustomer(), "Edison");
    }

    @Test
    public void testMethodChaining() {
        var order = forCustomer("Edison").buyPart(1, "Exhaust").at(100).end();
        System.out.println("order = " + order);
    }
}