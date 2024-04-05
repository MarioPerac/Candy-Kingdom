package factory.model;

import java.io.Serializable;

public class OrderedProduct implements Serializable {

    private String name;
    private Double price;
    private int selectedQuantity;

    public OrderedProduct() {
    }

    public OrderedProduct(String name, Double price, int selectedQuantity) {
        this.name = name;
        this.price = price;
        this.selectedQuantity = selectedQuantity;
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

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    @Override
    public String toString() {
        return
                "name= " + name +
                        ", price=" + price +
                        "$, selectedQuantity=" + selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }
}
