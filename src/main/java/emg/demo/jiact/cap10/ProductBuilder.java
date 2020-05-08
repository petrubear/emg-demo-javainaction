package emg.demo.jiact.cap10;

import emg.demo.jiact.cap10.model.Product;

public class ProductBuilder {

    private final MethodChainingOrderBuilder builder;
    public final Product product = new Product();

    public ProductBuilder(MethodChainingOrderBuilder builder, Product.Type type, int quantity, String name) {
        this.builder = builder;
        product.setType(type);
        product.setQuantity(quantity);
        product.setName(name);
    }

    public MethodChainingOrderBuilder at(double price) {
        product.setPrice(price);
        return this.builder.addProduct(product);
    }
}
