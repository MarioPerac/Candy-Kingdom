package factory.controller;

import factory.DistributorApplication;
import factory.model.Distributor;
import factory.model.Product;
import factory.model.Repository;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable{

    public TableView<Product> productsTable;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, Double> priceColumn;
    public TableColumn<Product, Integer> quantityColumn;
    public Label distributorNameLabel;

    public void onLogOutButtonClick(MouseEvent mouseEvent) {
        Controller.changeScene("login-view.fxml", mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productsTable.setItems(Repository.getInstance().getProducts());
        distributorNameLabel.setText(Distributor.getInstance().getName());
    }



    public void onAddProductButtonClick(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(DistributorApplication.class.getResource("view/add_products-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
