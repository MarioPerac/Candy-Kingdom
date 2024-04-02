package factory.service;

import factory.model.Product;
import factory.repository.ProductRepository;

import java.util.List;
import java.util.Map;

public class ProductService {
    ProductRepository productRepository = new ProductRepository();

    public List<Product> getAll() {
        return productRepository.getAll();
    }
}
