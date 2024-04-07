package testData;

import factory.model.Product;
import factory.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = new ArrayList<>();


        products.add(new Product("Milka čokolada", 1.99, 20));
        products.add(new Product("Kinder Bueno", 2.49, 15));
        products.add(new Product("Ferrero Rocher", 3.99, 10));
        products.add(new Product("Toblerone", 2.79, 12));
        products.add(new Product("Snickers", 1.49, 25));
        products.add(new Product("Twix", 1.79, 18));
        products.add(new Product("KitKat", 1.29, 22));
        products.add(new Product("M&M's", 1.99, 20));
        productRepository.addList(products);
    }
}
