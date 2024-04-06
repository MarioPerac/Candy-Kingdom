package factory.distributorapp.controller;

import factory.distributorapp.DistributorApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public interface Controller {

    public static void changeScene(String fxmlFile, MouseEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(DistributorApplication.class.getResource("view/" + fxmlFile));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Candy Kingdom Distributor");
        stage.setScene(scene);
        fxmlLoader.getController();
        stage.show();
    }
}
