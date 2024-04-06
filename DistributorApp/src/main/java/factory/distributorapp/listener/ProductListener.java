package factory.distributorapp.listener;

import factory.distributorapp.model.Product;

import java.util.List;

public interface ProductListener {

    public void add(Product product);
    public List<Product> getAll();
}
