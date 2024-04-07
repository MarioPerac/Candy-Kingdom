package factory;

import factory.rmi.DistributorServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class DistributorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DistributorApplication.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Candy Kingdom Distributor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        try {
            DistributorServer.getInstance().stopServer();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}