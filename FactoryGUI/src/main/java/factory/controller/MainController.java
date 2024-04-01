package factory.controller;

import factory.FactoryApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {


    public BorderPane borderPane;

    public void onUsersButtonClick(MouseEvent mouseEvent) {

        Parent root = null;

        try {
            root = FXMLLoader.load(FactoryApplication.class.getResource("view/users-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(root);
    }
}