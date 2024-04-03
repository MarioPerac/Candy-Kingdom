package factory.controller;

import factory.model.Order;
import factory.model.OrderedProduct;
import factory.model.Product;
import factory.model.UserInfo;
import factory.service.OrderService;
import factory.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    public TableView<Product> productsTable;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, Double> priceColumn;
    public TableColumn<Product, Integer> quantityColumn;
    public TableColumn<Product, Integer> selectQuantityColumn;

    private ObservableList<Product> products;

    OrderService orderService = new OrderService();

    ProductService productService = new ProductService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products = FXCollections.observableArrayList(productService.getAll());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        selectQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("selectedQuantity"));
        productsTable.setItems(products);
        setSelectedColumn();

    }

    private void setSelectedColumn() {
        selectQuantityColumn.setCellFactory(tc -> new TableCell<>() {
            private final Spinner<Integer> spinner = new Spinner<>(0, Integer.MAX_VALUE, 0, 1);

            {
                spinner.setEditable(true);
                spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (!isEmpty()) {
                        Product product = getTableRow().getItem();
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


    public void onBuyButtonClick(MouseEvent mouseEvent) {

        ArrayList<OrderedProduct> orderedProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.getSelectedQuantity() > 0) {
                orderedProducts.add(new OrderedProduct(p.getName(), p.getPrice(), p.getSelectedQuantity()));
            }
        }

        UserInfo userInfo = UserInfo.getInstance();
        orderService.create(new Order(orderedProducts, userInfo.getUsername(), userInfo.getEmail()));
        productService.decreaseProductsQuantity(orderedProducts);
        products.removeAll();
        products.setAll(productService.getAll());
    }
}
