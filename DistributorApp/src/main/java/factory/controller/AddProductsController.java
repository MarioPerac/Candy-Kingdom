package factory.controller;

import factory.model.Product;
import factory.model.Repository;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;e

public class AddProductsController {
    public TextField quantityTextField;
    public TextField priceTextField;
    public TextField nameTextField;

    public void onAddButtonClick(MouseEvent mouseEvent) {
        String name = nameTextField.getText();
        Double price =Double.parseDouble(priceTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());

        Repository.getInstance().add(new Product(name, price,quantity));
    }

}
