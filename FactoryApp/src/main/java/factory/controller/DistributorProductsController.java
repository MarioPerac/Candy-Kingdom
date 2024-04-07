package factory.controller;

import factory.model.DistributorProduct;
import factory.model.Product;
import factory.rmi.DistributorInterface;
import factory.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

public class DistributorProductsController implements Initializable {

    public TableView<DistributorProduct> productsTable;
    public TableColumn<DistributorProduct, String> nameColumn;
    public TableColumn<DistributorProduct, Double> priceColumn;
    public TableColumn<DistributorProduct, Integer> quantityColumn;
    public TableColumn<DistributorProduct, Integer> selectQuantityColumn;

    private ObservableList<DistributorProduct> products;

    private DistributorInterface distributor;

    private ProductService productService = new ProductService();

    public DistributorInterface getDistributor() {
        return distributor;
    }

    public void setDistributor(DistributorInterface distributor) {
        this.distributor = distributor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        selectQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("selectedQuantity"));
        productsTable.setItems(products);
        setSelectedColumn();
    }

    public void setProducts(List<Product> productList) {
        for (Product product : productList) {
            products.add(DistributorProduct.fromProduct(product));
        }
    }

    public void onBuyButtonClick(MouseEvent mouseEvent) {

        HashMap<String, Integer> boughtProducts = new HashMap<>();
        List<Product> newProducts = new ArrayList<>();
        for (DistributorProduct p : products) {
            if (p.getSelectedQuantity() > 0) {
                boughtProducts.put(p.getName(), p.getSelectedQuantity());
                newProducts.add(new Product(p.getName(), p.getPrice(), p.getSelectedQuantity()));
            }
        }

        if (!boughtProducts.isEmpty()) {
            try {
                distributor.buyProduct(boughtProducts);
                productService.addProducts(newProducts);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            showAlertAndWait(Alert.AlertType.CONFIRMATION, "Buying confirmation", "Products bought successfully.");
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        } else {
            showAlertAndWait(Alert.AlertType.ERROR, "Buying error", "No selected products.");
        }
    }

    private void setSelectedColumn() {
        selectQuantityColumn.setCellFactory(tc -> new TableCell<>() {
            private final Spinner<Integer> spinner = new Spinner<>(0, Integer.MAX_VALUE, 0, 1);

            {
                spinner.setEditable(true);
                spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (!isEmpty()) {
                        DistributorProduct product = getTableRow().getItem();
                        if (product != null && product.getQuantity() >= newValue) {
                            product.setSelectedQuantity(newValue);
                        } else {
                            spinner.decrement();
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(spinner);
                }
            }
        });
    }

    private void showAlertAndWait(Alert.AlertType type, String title, String context) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(context);
        alert.showAndWait();
    }

}
