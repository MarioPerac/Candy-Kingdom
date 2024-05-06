package factory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;


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

    public void decreaseProductQuantity(String name, Integer quantity){

        for(int i=0; i< products.size(); i++){
            if(products.get(i).getName().equals(name)){
               int newQuantity = products.get(i).getQuantity() - quantity;
               double price = products.get(i).getPrice();
               delete(products.get(i));
               if(newQuantity != 0)
                   products.add(new Product(name, price, newQuantity));
            }
        }
    }
}
