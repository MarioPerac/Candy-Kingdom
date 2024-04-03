package factory.service;

import factory.model.Product;
import factory.repository.ProductRepository;

import java.util.List;

public class ProductService {
    ProductRepository productRepository = new ProductRepository();

    public List<Product> getAllAvailable() {
        return productRepository.getAllAvailable();
    }
}
