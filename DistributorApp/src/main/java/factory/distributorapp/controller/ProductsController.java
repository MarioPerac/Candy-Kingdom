package factory.distributorapp.controller;

import factory.distributorapp.DistributorApplication;
import factory.distributorapp.listener.ProductListener;
import factory.distributorapp.model.Distributor;
import factory.distributorapp.model.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductsController implements Initializable, ProductListener{

    public TableView<Product> productsTable;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, Double> priceColumn;
    public TableColumn<Product, Integer> quantityColumn;
    public Label distributorNameLabel;

    private ObservableList<Product> products;

    public void onLogOutButtonClick(MouseEvent mouseEvent) {
        Controller.changeScene("login-view.fxml", mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productsTable.setItems(products);
        distributorNameLabel.setText(Distributor.getInstance().getName());
    }


    @Override
    public void add(Product product) {
        products.add(product);
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
            AddProductsController addProductsController = fxmlLoader.getController();
            addProductsController.setProductListener(this);
            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
