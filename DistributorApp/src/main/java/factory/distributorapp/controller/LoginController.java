package factory.distributorapp.controller;

import factory.distributorapp.model.Distributor;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
    public TextField distriubtorTextField;

    public void onLoginButtonClick(MouseEvent mouseEvent) {


        String name = distriubtorTextField.getText();
        if(!name.isEmpty()) {
            Distributor.getInstance().setName(name);
            Controller.changeScene("products-view.fxml", mouseEvent);
        }
    }
}
