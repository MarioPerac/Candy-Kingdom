package factory.controller;

import factory.FactoryApplication;
import factory.model.Product;
import factory.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FactoryProductsController implements Initializable {

    public TableView<Product> productsTable;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, Double> priceColumn;
    public TableColumn<Product, Integer> quantityColumn;

    public ObservableList<Product> products;
    public TableColumn<Product, Button> updateColumn;
    public TableColumn<Product, Button> deleteColumn;
    private ProductService productService = new ProductService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products = FXCollections.observableArrayList(productService.getAll());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        setUpdateColumn();
        setDeleteColumn();
        productsTable.setItems(products);
    }


    private void setUpdateColumn() {
        updateColumn.setCellFactory(tc -> new TableCell<>() {
            private final Button button = new Button("Update");

            {
                button.setOnMouseClicked(event -> {
                    if (!isEmpty()) {
                        Product product = getTableView().getItems().get(getIndex());
                        openUpdateDialog(product, event);
                    }
                });
            }

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
    }

    private void openUpdateDialog(Product product, MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(FactoryApplication.class.getResource("view/update_products-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            UpdateProductsController controller = fxmlLoader.getController();
            controller.setProduct(product);
            dialogStage.showAndWait();
            products.removeAll();
            products.setAll(productService.getAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteColumn() {
        deleteColumn.setCellFactory(tc -> new TableCell<>() {
            private Button button = new Button("Delete");

            {
                button.setOnMouseClicked(event -> {
                    if (!isEmpty()) {
                        Product product = getTableRow().getItem();
                        productService.deleteProduct(product.getName());
                        products.remove(product);
                    }
                });
            }

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
    }

}
