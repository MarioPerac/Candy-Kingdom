package factory.controller;

import factory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    public TableView<Product> productsTable;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, Double> priceColumn;
    public TableColumn<Product, Integer> quantityColumn;

    public static ObservableList<Product> productsInCart = FXCollections.observableArrayList();
    public TableColumn<Product, Button> removeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("selectedQuantity"));
        setRemoveColumn();
        productsTable.setItems(productsInCart);
    }


    private void setRemoveColumn() {
        removeColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Product, Button> call(TableColumn<Product, Button> param) {
                return new TableCell<>() {
                    private final Button removeButton = new Button();

                    {
                        removeButton.setText("Remove from Cart");
                        removeButton.setOnAction(event -> {
                            Product p = getTableView().getItems().get(getTableRow().getIndex());
                            CartController.productsInCart.remove(p);
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(removeButton);
                        }
                    }
                };
            }
        });

    }

    //to do
    public void onBuyButtonClick(MouseEvent mouseEvent) {
    }
}
