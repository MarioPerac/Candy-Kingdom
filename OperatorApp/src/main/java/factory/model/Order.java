package factory.model;

import java.util.List;

public class Order {

    private List<OrderedProduct> products;
    private String customerUsername;
    private String customerEmail;

    public Order() {
    }

    public Order(List<OrderedProduct> products, String customerUsername, String customerEmail) {
        this.products = products;
        this.customerUsername = customerUsername;
        this.customerEmail = customerEmail;
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
