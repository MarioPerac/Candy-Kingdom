package factory.controller;

import factory.model.Product;
import factory.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    public TableView<Product> productsTable;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, Double> priceColumn;
    public TableColumn<Product, Integer> quantityColumn;
    public TableColumn<Product, Button> addColumn;
    public TableColumn<Product, Integer> selectQuantityColumn;

    private ObservableList<Product> products;

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
        setAddToCartColumn();

    }

    private void setAddToCartColumn() {
        addColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Product, Button> call(TableColumn<Product, Button> param) {
                return new TableCell<>() {
                    private final Button addButton = new Button();

                    {
                        addButton.setText("Add to Cart");
                        addButton.setOnAction(event -> {
                            Product p = getTableView().getItems().get(getTableRow().getIndex());

                            CartController.productsInCart.add(p);
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Product p = getTableView().getItems().get(getTableRow().getIndex());
                            if (p.getQuantity() == 0)
                                addButton.setDisable(true);

                            setGraphic(addButton);
                        }
                    }
                };
            }
        });

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


}
