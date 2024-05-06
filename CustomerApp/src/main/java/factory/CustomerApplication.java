package factory;

import factory.service.MulticastService;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class CustomerApplication extends Application {

    private static MulticastService multicastService;


    public static void closeCustomerService() {
        if (multicastService != null)
            multicastService.stopService();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerApplication.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        multicastService = new MulticastService();
        multicastService.start();
        launch();
        multicastService.stopService();

    }
}