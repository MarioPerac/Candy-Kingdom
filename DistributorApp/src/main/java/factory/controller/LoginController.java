package factory.controller;

import factory.model.Distributor;
import factory.rmi.DistributorServer;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.rmi.RemoteException;

public class LoginController {
    public TextField distriubtorTextField;

    public void onLoginButtonClick(MouseEvent mouseEvent) {


        String name = distriubtorTextField.getText();
        if(!name.isEmpty()) {
            Distributor.getInstance().setName(name);
            try {
                DistributorServer.getInstance().start();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            Controller.changeScene("products-view.fxml", mouseEvent);
        }
    }
}
