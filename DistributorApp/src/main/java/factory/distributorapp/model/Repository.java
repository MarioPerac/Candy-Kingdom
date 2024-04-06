package factory.distributorapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Repository {

    private ObservableList<Product> products = FXCollections.observableArrayList();

    private static Repository instance;

    public static Repository getInstance(){
        if(instance == null)
            instance = new Repository();

        return instance;
    }

    public ObservableList<Product> getProducts(){
        return products;
    }

    public void add(Product product){
        products.add(product);
    }

    public void delete(Product product){
        products.remove(product);
    }
}
