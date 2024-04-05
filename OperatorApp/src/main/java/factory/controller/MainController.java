package factory.controller;

import factory.model.Order;
import factory.service.OrderService;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class MainController {

    private OrderService orderService = new OrderService();

    public void onGetOrderButton(MouseEvent mouseEvent) {

        Order order = orderService.get();

        if (order == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Order Available");
            alert.setHeaderText(null);
            alert.setContentText("No order is currently available.");
            alert.showAndWait();
        } else {
            OrderController.order = order;
            Controller.changeScene("order-view.fxml", mouseEvent);
        }


    }
}
