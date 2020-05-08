package emg.demo.jiact.cap10.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String customer;
    private final List<Product> products = new ArrayList<>();

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getValue() {
        return products.stream().mapToDouble(Product::getValue).sum();
    }

    @Override
    public String toString() {
        return "Order{" +
            "customer='" + getCustomer() + '\'' +
            "vaue=$" + getValue() + "" +
            ", products=" + products +
            '}';
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
