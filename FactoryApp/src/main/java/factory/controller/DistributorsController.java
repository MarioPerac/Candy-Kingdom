package factory.controller;

import factory.FactoryApplication;
import factory.logger.AppLogger;
import factory.rmi.DistributorClient;
import factory.rmi.DistributorInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

public class DistributorsController {
    public VBox mainVBox;


    public void addDistributors(String[] distributors) {
        for (String distributor : distributors) {
            Button button = new Button(distributor);
            button.setPrefSize(150, 40);
            button.setOnMouseClicked(event -> {
                String name = button.getText();
                onDistributorButtonClick(event, DistributorClient.getInstance().getDistributor(name));
            });
            mainVBox.getChildren().add(button);
        }
    }


    public void onDistributorButtonClick(MouseEvent mouseEvent, DistributorInterface distributor) {
        FXMLLoader fxmlLoader = new FXMLLoader(FactoryApplication.class.getResource("view/distributor_products-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Buy Products");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            DistributorProductsController controller = fxmlLoader.getController();
            controller.setDistributor(distributor);
            controller.setProducts(distributor.getProducts());
            dialogStage.showAndWait();
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
}
