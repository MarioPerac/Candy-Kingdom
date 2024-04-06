package factory;

import factory.service.MulticastService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerApplication.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        MulticastService multicastService = new MulticastService();
        multicastService.start();
        launch();
        multicastService.stopService();
    }
}