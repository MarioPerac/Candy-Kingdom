package factory.controller;

import factory.model.Mail;
import factory.model.Order;
import factory.model.OrderStatus;
import factory.model.OrderedProduct;
import factory.service.MailService;
import factory.service.OperatorService;
import factory.service.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    public TableView<OrderedProduct> productsTable;
    public TableColumn<OrderedProduct, String> nameColumn;
    public TableColumn<OrderedProduct, Double> priceColumn;
    public TableColumn<OrderedProduct, Integer> quantityColumn;

    public static ObservableList<OrderedProduct> products;
    public Label usernameLabel;
    public Label emailLabel;

    public static Order order;
    private MailService mailService = new MailService();
    private OperatorService operatorService = new OperatorService();

    public void onAcceptButtonClick(MouseEvent mouseEvent) {
        boolean result = mailService.sendMail(new Mail(order.getCustomerEmail(), order.getCustomerUsername(), OrderStatus.ACCEPTED.toString(), order.getProducts()));
        Alert alert;
        if (result) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mail sent");
            alert.setHeaderText(null);
            alert.setContentText("Mail sent successfully!");
            operatorService.saveOrder(order, OrderStatus.ACCEPTED.toString());
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mail error");
            alert.setHeaderText(null);
            alert.setContentText("Mail is not sent!");
        }
        alert.showAndWait();

        Controller.changeScene("main-view.fxml", mouseEvent);
    }

    public void onRejectButtonClick(MouseEvent mouseEvent) {
        boolean result = mailService.sendMail(new Mail(order.getCustomerEmail(), order.getCustomerUsername(), OrderStatus.REJECTED.toString(), order.getProducts()));
        Alert alert;
        if (result) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mail sent");
            alert.setHeaderText(null);
            alert.setContentText("Mail sent successfully!");
            operatorService.saveOrder(order, OrderStatus.REJECTED.toString());
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mail error");
            alert.setHeaderText(null);
            alert.setContentText("Mail is not sent!");
        }
        alert.showAndWait();
        Controller.changeScene("main-view.fxml", mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("selectedQuantity"));
        usernameLabel.setText(order.getCustomerUsername());
        emailLabel.setText(order.getCustomerEmail());
        products = FXCollections.observableArrayList(order.getProducts());
        productsTable.setItems(products);
    }

}
