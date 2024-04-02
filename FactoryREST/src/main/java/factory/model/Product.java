package factory.model;

import java.util.HashMap;

public class Product {
    private String name;
    private Double price;

    private int quantity;

    public Product() {
    }

    public Product(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("price", price.toString());
        data.put("quantity", String.valueOf(quantity));
        return data;
    }
}
