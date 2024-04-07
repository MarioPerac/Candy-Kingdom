package factory.controller;

import factory.model.Product;
import factory.service.ProductService;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UpdateProductsController {
    public TextField nameTextField;
    public TextField priceTextField;
    public TextField quantityTextField;

    private Product product;
    private ProductService productService = new ProductService();

    public void setProduct(Product product) {
        this.product = product;
        setName(product.getName());
        setPrice(product.getPrice());
        setQuantity(product.getQuantity());
    }

    public void onUpdateButtonClick(MouseEvent mouseEvent) {
        String name = nameTextField.getText();
        Double price = Double.parseDouble(priceTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());
        String previousName = product.getName();

        product = new Product(name, price, quantity);
        productService.updateProduct(previousName, product);
    }

    public void setName(String name) {
        nameTextField.setText(name);
    }

    public void setPrice(Double price) {
        priceTextField.setText(String.valueOf(price));
    }

    public void setQuantity(int quantity) {
        quantityTextField.setText(String.valueOf(quantity));
    }

}
