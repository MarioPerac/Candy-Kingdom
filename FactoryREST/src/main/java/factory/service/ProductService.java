package factory.service;

import factory.model.OrderedProduct;
import factory.model.Product;
import factory.repository.ProductRepository;

import java.util.List;

public class ProductService {
    ProductRepository productRepository = new ProductRepository();

    public List<Product> getAllAvailable() {
        return productRepository.getAllAvailable();
    }

    public void decreaseProductQuantity(List<OrderedProduct> orderedProducts) {
        productRepository.decreaseProductQuantity(orderedProducts);
    }


    public void increaseProductQuantity(List<OrderedProduct> orderedProducts) {
        productRepository.increaseProductQuantity(orderedProducts);
    }

    public void add(Product product) {
        productRepository.add(product);
    }

    public void add(List<Product> products) {
        productRepository.addList(products);
    }

    public boolean delete(String name) {
        return productRepository.delete(name);
    }

    public void update(String name, Product product) {
        productRepository.update(name, product);
    }
}
