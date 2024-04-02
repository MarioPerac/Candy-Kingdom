package factory.controller;

import factory.CustomerApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public interface Controller {

    public static void changeScene(String fxmlFile, MouseEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerApplication.class.getResource("view/" + fxmlFile));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Candy Kingdom");
        stage.setScene(scene);
        fxmlLoader.getController();
        stage.show();
    }
}
