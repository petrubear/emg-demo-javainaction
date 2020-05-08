package emg.demo.jiact.cap10;

import emg.demo.jiact.cap10.model.Order;
import emg.demo.jiact.cap10.model.Product;

public class MethodChainingOrderBuilder {
    public final Order order = new Order();

    private MethodChainingOrderBuilder(String customer) {
        order.setCustomer(customer);
    }

    public static MethodChainingOrderBuilder forCustomer(String customer) {
        return new MethodChainingOrderBuilder(customer);
    }

    public Order end() {
        return order;
    }

    public ProductBuilder buyGear(int quantity, String name) {
        return new ProductBuilder(this, Product.Type.GEAR, quantity, name);
    }

    public ProductBuilder buyPart(int quantity, String name) {
        return new ProductBuilder(this, Product.Type.PART, quantity, name);
    }

    public MethodChainingOrderBuilder addProduct(Product product) {
        order.addProduct(product);
        return this;
    }
}
