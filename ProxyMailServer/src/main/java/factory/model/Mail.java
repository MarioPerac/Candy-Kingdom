package factory.model;

import java.io.Serializable;
import java.util.List;

public class Mail implements Serializable {
    private String to;
    private String toUsername;
    private String status;
    private List<OrderedProduct> productList;

    public Mail() {

    }

    public Mail(String to, String toUsername, String status, List<OrderedProduct> productList) {
        this.to = to;
        this.toUsername = toUsername;
        this.status = status;
        this.productList = productList;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderedProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderedProduct> productList) {
        this.productList = productList;
    }
}
