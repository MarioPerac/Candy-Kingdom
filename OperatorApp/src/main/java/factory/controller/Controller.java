package factory.controller;

import factory.OperatorApplication;
import factory.logger.AppLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

public interface Controller {

    public static void changeScene(String fxmlFile, MouseEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(OperatorApplication.class.getResource("view/" + fxmlFile));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
        stage.setTitle("Candy Kingdom");
        stage.setScene(scene);
        fxmlLoader.getController();
        stage.show();
    }
}
