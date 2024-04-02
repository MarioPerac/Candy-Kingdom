package testData;

import factory.model.Product;
import factory.repository.ProductRepository;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = new ArrayList<>();


        products.add(new Product("Product 1", 10.99, 5));
        products.add(new Product("Product 2", 15.49, 10));
        products.add(new Product("Product 3", 8.99, 3));
        products.add(new Product("Product 4", 20.99, 8));
        products.add(new Product("Product 5", 12.79, 6));
        products.add(new Product("Product 6", 18.59, 12));
        products.add(new Product("Product 7", 9.29, 4));
        products.add(new Product("Product 8", 14.99, 7));
        products.add(new Product("Product 9", 11.39, 9));
        products.add(new Product("Product 10", 16.79, 11));
        productRepository.addList(products);
    }
}
