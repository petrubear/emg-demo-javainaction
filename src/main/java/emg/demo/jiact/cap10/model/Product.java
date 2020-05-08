package emg.demo.jiact.cap10.model;

public class Product {
    public enum Type {
        PART,
        GEAR
    }

    private Type type;
    private String name;
    private int quantity;
    private double price;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "Product{" +
            "type=" + type +
            ", name='" + name + '\'' +
            ", quantity=" + quantity +
            ", price=$" + price +
            '}';
    }
}
