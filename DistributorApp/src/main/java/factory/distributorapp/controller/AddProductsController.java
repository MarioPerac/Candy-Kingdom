package factory.distributorapp.controller;

import factory.distributorapp.listener.ProductListener;
import factory.distributorapp.model.Product;
import factory.distributorapp.model.Repository;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProductsController {
    public TextField quantityTextField;
    public TextField priceTextField;
    public TextField nameTextField;

    private ProductListener productListener;

    public void onAddButtonClick(MouseEvent mouseEvent) {
        String name = nameTextField.getText();
        Double price =Double.parseDouble(priceTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());

        Repository.getInstance().add(new Product(name, price,quantity));
    }

    public void setProductListener(ProductListener productListener) {
        this.productListener = productListener;
    }

}
