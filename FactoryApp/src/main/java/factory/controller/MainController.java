package factory.controller;

import factory.FactoryApplication;
import factory.rmi.DistributorClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {


    public BorderPane borderPane;


    public void onMessageButtonClick(MouseEvent mouseEvent) {
        Parent root = null;

        try {
            root = FXMLLoader.load(FactoryApplication.class.getResource("view/message-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(root);
    }

    public void onUsersButtonClick(MouseEvent mouseEvent) {
        Parent root = null;

        try {
            root = FXMLLoader.load(FactoryApplication.class.getResource("view/users-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(root);
    }

    public void onDistributorsButtonClick(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(FactoryApplication.class.getResource("view/distributors-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            DistributorsController distributorsController = loader.getController();
            distributorsController.addDistributors(DistributorClient.getInstance().getDistributors());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(root);

    }

    public void onProductsButtonClick(MouseEvent mouseEvent) {
        Parent root = null;

        try {
            root = FXMLLoader.load(FactoryApplication.class.getResource("view/factory_products-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(root);
    }
}