package factory.controller;

import factory.CustomerApplication;
import factory.model.UserInfo;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    
    public BorderPane borderPane;

    public void onLogoutButtonClicked(MouseEvent mouseEvent) {
        Controller.changeScene("login-view.fxml", mouseEvent);
    }

    public void onProductsButtonClick(MouseEvent mouseEvent) {

        Parent root = null;

        try {
            root = FXMLLoader.load(CustomerApplication.class.getResource("view/products.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(root);
    }


}
